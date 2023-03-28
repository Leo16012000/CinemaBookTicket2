package com.leo.dtos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ResponseDto {
    public Object getPayload() {
        return payload;
    }

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    private String status;
    @JacksonXmlProperty(localName = "Payload")
    private Object payload;
}
