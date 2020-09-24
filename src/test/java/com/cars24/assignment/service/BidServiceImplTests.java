package com.cars24.assignment.service;

import com.cars24.assignment.exception.AuctionNotFoundException;
import com.cars24.assignment.exception.AuctionOverException;
import com.cars24.assignment.model.BidRequest;
import com.cars24.assignment.model.BidResponse;
import com.cars24.assignment.model.LoggedInUserDetails;
import com.cars24.assignment.model.jpa.Auction;
import com.cars24.assignment.model.jpa.BiddingDetails;
import com.cars24.assignment.model.jpa.UserDetails;
import com.cars24.assignment.repository.AuctionRepository;
import com.cars24.assignment.repository.BiddingRepository;
import com.cars24.assignment.utils.AuthHelper;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Objects;


public class BidServiceImplTests {

  private static final String BID_ACCEPTED = "Accepted";
  private static final String BID_REJECTED = "Recjected";
  private static final String AUCTION_OVER = "OVER";
  private static final String AUCTION_RUNNING = "RUNNING";
  @Mock
  private AuctionRepository auctionRepository;
  @Mock
  private BiddingRepository biddingRepository;
  @Mock
  private AuthHelper authHelper;
  private BidServiceImpl bidService;
  
  private static final String accessToken = "sdfasdfadsgadfhsrdgfdsagfdasf";

  @BeforeEach
  public void init() {
      MockitoAnnotations.initMocks(this);
      bidService = new BidServiceImpl(auctionRepository, biddingRepository, authHelper);
      when(authHelper.validateUser(Mockito.anyString())).thenReturn(LoggedInUserDetails.builder().email("abc@cars24.com").build());
  }
  
 
  @Test
  public void noAuctionFound() {
      when(auctionRepository.getByItemId(Mockito.anyInt())).thenReturn(null);
      Assertions.assertThrows(AuctionNotFoundException.class, ()->bidService.raiseBid(getBidRequest(), accessToken));
  }
  
  @Test
  public void AuctionOver() {
      Auction auction = new Auction();
      auction.setAuctionStatus(AUCTION_OVER);
      when(auctionRepository.getByItemId(Mockito.anyInt())).thenReturn(auction);
      Assertions.assertThrows(AuctionOverException.class, ()->bidService.raiseBid(getBidRequest(), accessToken));
  }
  
  @Test
  public void updateHighestBid() {
      Auction auction = new Auction();
      auction.setAuctionStatus(AUCTION_RUNNING);
      auction.setHighestBid(1000);
      auction.setStepRate(250);
      when(auctionRepository.getByItemId(Mockito.anyInt())).thenReturn(auction);
      when(auctionRepository.save(Mockito.any())).thenReturn(auction);
      when(biddingRepository.save(Mockito.any())).thenReturn(getBiddingDetails());
      BidResponse response = bidService.raiseBid(getBidRequest(), accessToken);
      
      assertNotNull(response);
      assertEquals(BID_ACCEPTED, response.getBidStatus());
      Mockito.verify(auctionRepository, times(1)).save(Mockito.any());
  }
  
  @Test
  public void bidRejected() {
      Auction auction = new Auction();
      auction.setAuctionStatus(AUCTION_RUNNING);
      auction.setHighestBid(2000);
      auction.setStepRate(250);
      when(auctionRepository.getByItemId(Mockito.anyInt())).thenReturn(auction);
      when(auctionRepository.save(Mockito.any())).thenReturn(auction);
      BiddingDetails biddingDetails = getBiddingDetails();
      biddingDetails.setBidStatus(BID_REJECTED);
      when(biddingRepository.save(Mockito.any())).thenReturn(biddingDetails);
      BidResponse response = bidService.raiseBid(getBidRequest(), accessToken);
      
      assertNotNull(response);
      assertEquals(BID_REJECTED, response.getBidStatus());
      Mockito.verify(auctionRepository, times(0)).save(Mockito.any());
  }
 
  
  private BidRequest getBidRequest() {
      
      BidRequest bidRequest = new BidRequest();
      bidRequest.setBidAmount(1500);
      bidRequest.setItemCode(1);
      return bidRequest;
  }
  
  private BiddingDetails getBiddingDetails() {
      BiddingDetails biddingDetails = new BiddingDetails();
      
      biddingDetails.setBidPrice(1500);
      biddingDetails.setBidStatus(BID_ACCEPTED);
      return biddingDetails;
  }

}
