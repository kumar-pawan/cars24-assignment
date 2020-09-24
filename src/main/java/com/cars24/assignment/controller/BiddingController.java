package com.cars24.assignment.controller;

import com.cars24.assignment.model.BidRequest;
import com.cars24.assignment.model.BidResponse;
import com.cars24.assignment.service.BiddingService;
import com.cars24.assignment.validation.Validations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BiddingController {

  private BiddingService biddingService;
  private Validations validations;

  /**
   * AuctionController.
   *
   * @param biddingService {BiddingService}
   * @param validations    {Validations}
   */
  public BiddingController(BiddingService biddingService, Validations validations) {

    this.biddingService = biddingService;
    this.validations = validations;
  }

  /**
   * ResponseEntity.
   *
   * @param bidRequest  {BidRequest}
   * @param accessToken {String}
   */
  @PostMapping(value = "/cars24/auctions/{itemCode}/bid")
  public ResponseEntity<BidResponse> raiseBid(@RequestBody BidRequest bidRequest,
      @PathVariable("itemCode") final int itemCode, @RequestHeader("accessToken") String accessToken) {
    log.info("Putting the bid");

    validations.checkAuthentication(accessToken);
    bidRequest.setItemCode(itemCode);

    return new ResponseEntity<BidResponse>(biddingService.raiseBid(bidRequest, accessToken), HttpStatus.CREATED);
  }
}
