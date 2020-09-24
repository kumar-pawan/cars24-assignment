package com.cars24.assignment.service;

import com.cars24.assignment.exception.AuctionNotFoundException;
import com.cars24.assignment.model.AuctionResponse;
import com.cars24.assignment.model.jpa.Auction;
import com.cars24.assignment.repository.AuctionRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuctionServiceImpl implements AuctionService {

  private AuctionRepository auctionRepository;

  public AuctionServiceImpl(AuctionRepository auctionRepository) {
    this.auctionRepository = auctionRepository;
  }

  @Override
  public List<AuctionResponse> getAuctionByStatus(String status) {

    try {
      log.info("Getting the auctions list");

      if (StringUtils.isEmpty(status)) {
        log.info("Not status given in the input, will fetch all the auction");
      }
      List<Auction> auctions = StringUtils.isEmpty(status) ? (List<Auction>) auctionRepository.findAll() :
          auctionRepository.getByAuctionStatus(status.toUpperCase());
      if (CollectionUtils.isEmpty(auctions)) {
        throw new AuctionNotFoundException("No auction found");
      }
      return mapResponse(auctions);
    }
    catch (Exception e) {
      log.error("Failed to get auctions {} ", e);
      throw new AuctionNotFoundException(e);
    }

  }

  private List<AuctionResponse> mapResponse(List<Auction> auctions) {

    return auctions.parallelStream().map(auction -> map(auction)).collect(Collectors.toList());
  }

  private final AuctionResponse map(Auction auction) {

    return AuctionResponse.builder().itemCode(auction.getItem().getId()).highestBid(auction.getHighestBid())
        .stepRate(auction.getStepRate()).build();
  }

}
