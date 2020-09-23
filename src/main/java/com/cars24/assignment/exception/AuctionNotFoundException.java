package com.cars24.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AuctionNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1552532359545423729L;

  public AuctionNotFoundException(String exception) {
    super(exception);
  }

  public AuctionNotFoundException(Throwable throwable) {
    super(throwable);
  }

  public AuctionNotFoundException(String message, Throwable throwable) {
    super(message, throwable);
  }

}
