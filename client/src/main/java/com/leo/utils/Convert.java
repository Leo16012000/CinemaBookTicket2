package com.leo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Convert {

    public String objectToXML(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = new XmlMapper();
        String xml = objectMapper.writeValueAsString(o);
        System.out.println(xml);
        return xml;
    }
}
