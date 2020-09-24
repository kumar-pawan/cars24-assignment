package com.cars24.assignment.model.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Auction {

  @Id
  private int id;
  @ManyToOne
  @JoinColumn(name = "item_id",
      referencedColumnName = "id")
  private ItemDetails item;
  private int minPrice;
  private int stepRate;
  private int highestBid;
  private String auctionStatus;
}
