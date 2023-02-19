package com.berkaytutal.andela.common.exception;

public class NotFoundException extends CustomException {

  public NotFoundException(Errors errors) {
    super(errors);
  }
}
