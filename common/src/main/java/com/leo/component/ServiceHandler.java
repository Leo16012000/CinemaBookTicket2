package com.leo.component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.leo.dtos.RequestDto;
import com.leo.dtos.ResponseDto;
import com.leo.utils.ObjectMappers;

public class ServiceHandler {
  private Logger logger = LogManager.getLogger(ServiceHandler.class);
  private static ServiceHandler instance;
  private Object authentication;

  public <T, K> ResponseDto<K> sendRequest(Socket socket, String serviceName, T payload,
      TypeReference<ResponseDto<K>> typeReference) throws IOException {

    RequestDto<T> request = RequestDto.<T>builder()
        .payload(payload)
        .serviceName(serviceName)
        .authentication(authentication)
        .build();

    DataOutputStream os = new DataOutputStream(socket.getOutputStream());
    String requestStr = ObjectMappers.getInstance().writeValueAsString(request);
    logger.debug(requestStr);
    os.writeUTF(requestStr);
    os.flush();

    DataInputStream is = new DataInputStream(socket.getInputStream());
    String responseStr = is.readUTF();
    logger.debug(responseStr);
    ResponseDto<K> response = ObjectMappers.getInstance().readValue(responseStr, typeReference);
    if (!"SUCCESS".equals(response.getStatus())) {
      logger.error(response.getTimestamp() + ": " + response.getMessage());
    }
    return response;
  }

  public void setAuthentication(Object authentication) {
    this.authentication = authentication;
  }

  public static ServiceHandler getInstance() {
    if (instance == null) {
      synchronized (ServiceHandler.class) {
        if (instance == null) {
          instance = new ServiceHandler();
        }
      }
    }
    return instance;
  }
}
