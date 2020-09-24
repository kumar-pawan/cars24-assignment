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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
public class BidServiceImpl implements BiddingService {

  private static final String BID_ACCEPTED = "Accepted";
  private static final String BID_REJECTED = "Recjected";
  private static final String AUCTION_OVER = "OVER";
  private AuctionRepository auctionRepository;
  private BiddingRepository biddingRepository;
  private AuthHelper authHelper;

  /**
   * BidServiceImpl.
   *
   * @param auctionRepository {AuctionRepository}
   * @param biddingRepository {BiddingRepository}
   * @param authHelper        {AuthHelper}
   */
  public BidServiceImpl(AuctionRepository auctionRepository, BiddingRepository biddingRepository,
      AuthHelper authHelper) {
    this.auctionRepository = auctionRepository;
    this.biddingRepository = biddingRepository;
    this.authHelper = authHelper;
  }

  /**
   * BidResponse.
   */
  @Override
  @Transactional(isolation = Isolation.REPEATABLE_READ)
  public BidResponse raiseBid(BidRequest bidRequest, String accessToken) {

    try {
      log.info("putting the new bid");
      LoggedInUserDetails userDetails = authHelper.validateUser(accessToken);
      log.info("user details {}", userDetails);
      Auction auction = auctionRepository.getByItemId(bidRequest.getItemCode());

      if (Objects.isNull(auction)) {

        throw new AuctionNotFoundException("No auction found for this item");
      }
      if (AUCTION_OVER.equalsIgnoreCase(auction.getAuctionStatus())) {
        throw new AuctionOverException("This Auction is over. Please wait for new auction");
      }
      String bidStatus = BID_REJECTED;
      String bidRejectionReason = "Auction is over or your invalid bid amount";
      if ((auction.getHighestBid() + auction.getStepRate()) <= bidRequest.getBidAmount()) {
        log.info("Updating the highest bid");
        bidRejectionReason = null;
        bidStatus = BID_ACCEPTED;
        auction.setHighestBid(bidRequest.getBidAmount());
        auctionRepository.save(auction);
      }
      BiddingDetails biddingDetails = getBiddingDetails(bidRequest, auction, userDetails, bidStatus);
      log.info("saving the user's bid");
      BiddingDetails response = biddingRepository.save(biddingDetails);

      return BidResponse.builder().bidStatus(response.getBidStatus()).rejectionReason(bidRejectionReason).build();

    }
    catch (Exception e) {
      log.error("Bid request failed {}", e);
      throw e;
    }
  }

  /**
   * BiddingDetails.
   *
   * @param bidRequest  {BidRequest}
   * @param auction     {Auction}
   * @param userDetails {LoggedInUserDetails}
   * @param bidStatus   {String}
   * @return BiddingDetails {BiddingDetails}
   */
  private BiddingDetails getBiddingDetails(BidRequest bidRequest, Auction auction, LoggedInUserDetails userDetails,
      String bidStatus) {
    BiddingDetails biddingDetails = new BiddingDetails();

    biddingDetails.setAuction(auction);
    biddingDetails.setBidStatus(bidStatus);
    biddingDetails.setBidPrice(bidRequest.getBidAmount());
    UserDetails user = new UserDetails();
    user.setEmail(userDetails.getEmail());
    biddingDetails.setUser(user);

    return biddingDetails;

  }

}
