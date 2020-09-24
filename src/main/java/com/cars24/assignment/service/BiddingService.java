package com.cars24.assignment.service;

import com.cars24.assignment.model.BidRequest;
import com.cars24.assignment.model.BidResponse;

public interface BiddingService {

  BidResponse raiseBid(BidRequest bidRequest, String accessToken);
}
