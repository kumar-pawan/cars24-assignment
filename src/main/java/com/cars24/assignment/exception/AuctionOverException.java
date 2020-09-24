package com.cars24.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AuctionOverException extends RuntimeException {

  private static final long serialVersionUID = 1552532359545423729L;

  public AuctionOverException(String exception) {
    super(exception);
  }

  public AuctionOverException(Throwable throwable) {
    super(throwable);
  }

  public AuctionOverException(String message, Throwable throwable) {
    super(message, throwable);
  }

}
