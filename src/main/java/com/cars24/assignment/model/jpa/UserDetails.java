package com.cars24.assignment.model.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UserDetails {

  @Id
  private String email;
  private String firstName;
  private String lastName;
  private long contactNumber;
}
