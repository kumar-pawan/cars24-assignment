package com.cars24.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends RuntimeException {

  private static final long serialVersionUID = 1552532359545423729L;

  public AuthenticationException(String exception) {
    super(exception);
  }

  public AuthenticationException(Throwable throwable) {
    super(throwable);
  }

  public AuthenticationException(String message, Throwable throwable) {
    super(message, throwable);
  }

}
