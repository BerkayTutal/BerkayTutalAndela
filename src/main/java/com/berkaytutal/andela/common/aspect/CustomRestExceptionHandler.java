package com.berkaytutal.andela.common.aspect;

import com.berkaytutal.andela.common.exception.CustomException;
import com.berkaytutal.andela.common.exception.NotFoundException;
import com.berkaytutal.andela.rest.dto.CustomExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomRestExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public CustomExceptionResponse handleNotFoundException(CustomException ce) {
    return CustomExceptionResponse.from(ce);
  }
}
