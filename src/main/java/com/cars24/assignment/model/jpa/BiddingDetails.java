package com.cars24.assignment.model.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class BiddingDetails {

  @Id
  private int id;
  @ManyToOne
  private Auction auction;
  @ManyToOne
  private UserDetails user;
  private String bidStatus;
}
