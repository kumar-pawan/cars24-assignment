package com.cars24.assignment.model.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class BiddingDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @ManyToOne
  @JoinColumn(name = "auction_id",
      referencedColumnName = "id")
  private Auction auction;
  @ManyToOne
  @JoinColumn(name = "user_id",
      referencedColumnName = "email")
  private UserDetails user;
  private String bidStatus;
  private int bidPrice;
}
