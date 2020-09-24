package com.cars24.assignment.controller;

import com.cars24.assignment.model.AuctionResponse;
import com.cars24.assignment.service.AuctionService;
import com.cars24.assignment.utils.PageHelper;
import com.cars24.assignment.validation.Validations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity<Page<AuctionResponse>> getAuctions(@RequestParam("status") String status,
      @RequestHeader("accessToken") String accessToken, Pageable pageable) {
    log.info("getting the auction list");

    validations.checkAuthentication(accessToken);
    Pageable updatedPageable = PageHelper.acutionSortingFieldMapper(pageable);

    return new ResponseEntity<Page<AuctionResponse>>(auctionService.getAuctionByStatus(status, updatedPageable),
        HttpStatus.OK);
  }
}
