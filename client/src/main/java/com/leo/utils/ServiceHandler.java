package com.leo.utils;

import com.leo.dtos.RequestDto;
import com.leo.dtos.ResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServiceHandler {
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
    os.writeUTF(ObjectMappers.getInstance().writeValueAsString(request));
    os.flush();
    DataInputStream is = new DataInputStream(socket.getInputStream());
    String response = is.readUTF();
    return ObjectMappers.getInstance().readValue(response, typeReference);
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
