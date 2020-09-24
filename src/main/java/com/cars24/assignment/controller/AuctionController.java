package com.cars24.assignment.controller;

import com.cars24.assignment.model.AuctionResponse;
import com.cars24.assignment.service.AuctionService;
import com.cars24.assignment.validation.Validations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class AuctionController {

  private AuctionService auctionService;
  private Validations validations;

  /**
   * AuctionController.
   *
   * @param auctionService {AuctionService}
   * @param validations    {Validations}
   */
  public AuctionController(AuctionService auctionService, Validations validations) {

    this.auctionService = auctionService;
    this.validations = validations;
  }

  /**
   * ResponseEntity.
   *
   * @param status      {String}
   * @param accessToken {String}
   */
  @GetMapping(value = "/cars24/auctions")
  public ResponseEntity<List<AuctionResponse>> getAuctions(@RequestParam("status") final String status,
      @RequestHeader("accessToken") String accessToken) {
    log.info("getting the auction list");

    validations.checkAuthentication(accessToken);

    return new ResponseEntity<List<AuctionResponse>>(auctionService.getAuctionByStatus(status), HttpStatus.OK);
  }
}
