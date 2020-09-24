package com.cars24.assignment.service;

import com.cars24.assignment.exception.AuctionNotFoundException;
import com.cars24.assignment.model.AuctionResponse;
import com.cars24.assignment.model.jpa.Auction;
import com.cars24.assignment.repository.AuctionRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
public class AuctionServiceImpl implements AuctionService {

  private AuctionRepository auctionRepository;

  public AuctionServiceImpl(AuctionRepository auctionRepository) {
    this.auctionRepository = auctionRepository;
  }

  @Override
  public Page<AuctionResponse> getAuctionByStatus(String status, Pageable pageable) {

    try {
      log.info("Getting the auctions list");

      if (StringUtils.isEmpty(status)) {
        log.info("Not status given in the input, will fetch all the auction");
      }
      Page<Auction> auctions = StringUtils.isEmpty(status) ? auctionRepository.findAll(pageable) :
          auctionRepository.getByAuctionStatus(status.toUpperCase(), pageable);
      if (CollectionUtils.isEmpty(auctions.getContent())) {
        throw new AuctionNotFoundException("No auction found");
      }
      return mapResponse(auctions);
    }
    catch (Exception e) {
      log.error("Failed to get auctions {} ", e);
      throw new AuctionNotFoundException(e);
    }

  }

  private Page<AuctionResponse> mapResponse(Page<Auction> auctionsPage) {

    log.info("page values {}", auctionsPage);
    return auctionsPage.map((auction) -> map(auction));
  }

  private final AuctionResponse map(Auction auction) {

    return AuctionResponse.builder().itemCode(auction.getItem().getId()).highestBid(auction.getHighestBid())
        .stepRate(auction.getStepRate()).build();
  }

}
