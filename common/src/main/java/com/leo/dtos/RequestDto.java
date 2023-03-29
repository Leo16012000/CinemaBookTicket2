package com.leo.dtos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
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
@JacksonXmlRootElement(localName = "requestDto")
public class RequestDto<T> {
  private String serviceName;
  @JacksonXmlElementWrapper(useWrapping = false)
  private T payload;
  private String id;
  private Object authentication;
  private boolean authenticated;
}
