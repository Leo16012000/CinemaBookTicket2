package com.leo.dtos;

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
  private T payload;
  private String id;
  private Object authentication;
  private boolean authenticated;
}
