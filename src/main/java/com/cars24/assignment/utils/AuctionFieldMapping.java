package com.cars24.assignment.utils;

public enum AuctionFieldMapping {

  itemCode("item_id"), highestBid("highest_bid"), stepRate("step_rate");

  private String value;

  AuctionFieldMapping(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
