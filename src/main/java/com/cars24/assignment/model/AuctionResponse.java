package com.cars24.assignment.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuctionResponse {

  private int itemCode;
  private int highestBid;
  private int stepRate;
}
