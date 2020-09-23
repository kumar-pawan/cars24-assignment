package com.cars24.assignment.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {

  private int code;
  private String message;
  private long timestamp;
  private String exception;
}
