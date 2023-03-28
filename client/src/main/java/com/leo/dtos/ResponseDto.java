package com.leo.dtos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.LinkedHashMap;

public class ResponseDto<T> {
    public T getPayload() {
        return payload;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    private String status;
    @JacksonXmlProperty(localName = "payload")
    private T payload;
}
