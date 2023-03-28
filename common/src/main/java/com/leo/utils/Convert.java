package com.leo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

public class Convert {
  public static <T> String objectToXML(T o) throws JsonProcessingException {
    return ObjectMappers.getInstance().writeValueAsString(o);
  }

  public static <T> T XMLToObject(String xmlPayload) throws JsonProcessingException {
    return ObjectMappers.getInstance().readValue(xmlPayload, new TypeReference<T>() {
    });
  }
}
