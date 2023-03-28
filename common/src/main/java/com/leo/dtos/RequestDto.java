package com.leo.dtos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto<T> {
  private String serviceName;
  @JacksonXmlProperty(localName = "payload")
  private T payload;
  private String id;
}
