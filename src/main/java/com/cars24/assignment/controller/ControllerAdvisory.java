package com.cars24.assignment.controller;

import com.cars24.assignment.exception.AuctionNotFoundException;
import com.cars24.assignment.exception.AuthenticationException;
import com.cars24.assignment.exception.InvalidBidException;
import com.cars24.assignment.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisory {

  /**
   * AuthenticationException.
   *
   * @return ResponseEntity
   */
  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> handleAuthenticationErrorResponse(AuthenticationException exception) {

    return new ResponseEntity<ErrorResponse>(
        ErrorResponse.builder().code(HttpStatus.UNAUTHORIZED.value()).message(exception.getMessage())
            .timestamp(System.currentTimeMillis()).exception(exception.getClass().getSimpleName()).build(),
        HttpStatus.UNAUTHORIZED);
  }

  /**
   * AuctionNotFoundException.
   *
   * @return ResponseEntity
   */
  @ExceptionHandler(value = AuctionNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleAuctionNotFoundErrorResponse(AuctionNotFoundException exception) {

    return new ResponseEntity<ErrorResponse>(
        ErrorResponse.builder().code(HttpStatus.NOT_FOUND.value()).message(exception.getMessage())
            .timestamp(System.currentTimeMillis()).exception(exception.getClass().getSimpleName()).build(),
        HttpStatus.NOT_FOUND);
  }

  /**
   * InvalidBidException.
   *
   * @return ResponseEntity
   */
  @ExceptionHandler(value = InvalidBidException.class)
  public ResponseEntity<ErrorResponse> handleInvalidBidErrorResponse(InvalidBidException exception) {

    return new ResponseEntity<ErrorResponse>(
        ErrorResponse.builder().code(HttpStatus.NOT_ACCEPTABLE.value()).message(exception.getMessage())
            .timestamp(System.currentTimeMillis()).exception(exception.getClass().getSimpleName()).build(),
        HttpStatus.NOT_ACCEPTABLE);
  }

  /**
   * Exception.
   *
   * @return ResponseEntity
   */
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericErrorResponse(Exception exception) {

    return new ResponseEntity<ErrorResponse>(
        ErrorResponse.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(exception.getMessage())
            .timestamp(System.currentTimeMillis()).exception(exception.getClass().getSimpleName()).build(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
