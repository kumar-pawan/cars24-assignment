package com.cars24.assignment.service;

import com.cars24.assignment.model.AuctionResponse;

import java.util.List;

public interface AuctionService {

  List<AuctionResponse> getAuctionByStatus(String status);
}
