package com.cars24.assignment.service;

import com.cars24.assignment.model.AuctionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuctionService {

  Page<AuctionResponse> getAuctionByStatus(String status, Pageable pageable);
}
