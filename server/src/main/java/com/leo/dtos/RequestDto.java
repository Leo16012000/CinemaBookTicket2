package com.leo.dtos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.LinkedHashMap;

public class RequestDto{
    public String getServiceName() {
        return serviceName;
    }

    public LinkedHashMap getPayload() {
        return payload;
    }

    private String serviceName;
    @JacksonXmlProperty(localName = "Payload")
    private LinkedHashMap payload;
}
