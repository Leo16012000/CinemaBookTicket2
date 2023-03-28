package com.leo.dtos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class RequestDto<T> {
  private String serviceName;
  @JacksonXmlProperty(localName = "payload")
  private T payload;
  private String id;
}
