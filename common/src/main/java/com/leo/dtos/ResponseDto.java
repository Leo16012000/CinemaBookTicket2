package com.leo.dtos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDto<T> {
  private String message;
  @JacksonXmlProperty(localName = "payload")
  private T payload;
  private String serviceName;
  private String id;
  private String status;
}
