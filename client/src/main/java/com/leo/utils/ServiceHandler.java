package com.leo.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.leo.dtos.RequestDto;
import com.leo.dtos.ResponseDto;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ServiceHandler {
    private static ServiceHandler instance;
    private Object authentication;

    public <T, K> ResponseDto<K> sendRequest(Socket socket, String serviceName, T payload,
                                             TypeReference<ResponseDto<K>> typeReference) throws IOException {
        String response= "";
        RequestDto<T> request = RequestDto.<T>builder()
                .payload(payload)
                .serviceName(serviceName)
                .authentication(authentication)
                .build();

        DataOutputStream os = new DataOutputStream(socket.getOutputStream());
        try {
            os.writeUTF(ObjectMappers.getInstance().writeValueAsString(request));
        os.flush();
            DataInputStream is = new DataInputStream(socket.getInputStream());
            response = is.readUTF();
        } catch (Exception e) {
            return new ResponseDto("", "", "PING", null, "SUCCESS");
        }
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
