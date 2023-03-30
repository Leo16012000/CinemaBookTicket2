package com.leo.dtos;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "responseDto")
public class ResponseDto<T> {
  private String message;
  @JacksonXmlElementWrapper(useWrapping = false)
  private T payload;
  private String serviceName;
  private String id;
  private String status;
  private OffsetDateTime timestamp;
}
