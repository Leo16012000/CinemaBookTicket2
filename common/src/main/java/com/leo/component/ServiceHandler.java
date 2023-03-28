package com.leo.component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.dtos.RequestDto;
import com.leo.dtos.ResponseDto;
import com.leo.utils.ObjectMappers;

public class ServiceHandler {
  public <T, K> ResponseDto<K> sendRequest(Socket socket, String serviceName, T payload,
      TypeReference<ResponseDto<K>> typeReference) throws IOException {
    RequestDto<T> request = RequestDto.<T>builder()
        .payload(payload)
        .serviceName(serviceName)
        .build();

    DataOutputStream os = new DataOutputStream(socket.getOutputStream());
    os.write(ObjectMappers.getInstance().writeValueAsString(request).getBytes());
    os.flush();

    DataInputStream is = new DataInputStream(socket.getInputStream());
    ObjectMapper objectMapper = ObjectMappers.getInstance();
    return objectMapper.readValue(is, typeReference);
  }
}
