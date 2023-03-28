package com.leo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ObjectMappers {
  private static ObjectMapper mapper;

  public static ObjectMapper getInstance() {
    if (mapper == null) {
      synchronized (ObjectMappers.class) {
        if (mapper == null) {
          mapper = new XmlMapper();
        }
      }
    }
    return mapper;
  }
}
