package com.cars24.assignment.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class LoggedInUserDetails implements Serializable {

  private static final long serialVersionUID = 3120506648203927161L;
  private String email;
  private long contact;
}
