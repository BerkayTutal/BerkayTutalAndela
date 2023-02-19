package com.berkaytutal.andela.rest.dto;

import com.berkaytutal.andela.common.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomExceptionResponse {

  private String message;

  public static CustomExceptionResponse from(CustomException customException) {
    return CustomExceptionResponse.builder()
        .message(customException.getErrors().getErrorDescription())
        .build();
  }
}
