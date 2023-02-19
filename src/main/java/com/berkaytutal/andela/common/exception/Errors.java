package com.berkaytutal.andela.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Errors {
  EPAPER_NOT_FOUND("Epaper is not found.");

  private final String errorDescription;

}
