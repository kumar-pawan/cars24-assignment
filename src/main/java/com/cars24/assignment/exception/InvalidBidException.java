package com.cars24.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class InvalidBidException extends RuntimeException {

  private static final long serialVersionUID = 1552532359545423729L;

  public InvalidBidException(String exception) {
    super(exception);
  }

  public InvalidBidException(Throwable throwable) {
    super(throwable);
  }

  public InvalidBidException(String message, Throwable throwable) {
    super(message, throwable);
  }

}
