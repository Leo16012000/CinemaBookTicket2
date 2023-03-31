package com.leo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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
