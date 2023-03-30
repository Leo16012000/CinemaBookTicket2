package com.leo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMappers {
  private static ObjectMapper mapper;

  public static ObjectMapper getInstance() {
    if (mapper == null) {
      synchronized (ObjectMappers.class) {
        if (mapper == null) {
          mapper = new XmlMapper();
          mapper.registerModule(new JavaTimeModule());
          mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }
      }
    }
    return mapper;
  }
}
