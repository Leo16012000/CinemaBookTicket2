package com.leo.dtos;

import lombok.Builder;

@Builder
public class SearchDto {
  private String key;

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

  private String value;
}
