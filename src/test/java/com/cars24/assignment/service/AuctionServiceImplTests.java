package com.cars24.assignment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cars24.assignment.model.AuctionResponse;
import com.cars24.assignment.model.jpa.Auction;
import com.cars24.assignment.model.jpa.ItemDetails;
import com.cars24.assignment.repository.AuctionRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Slf4j
public class AuctionServiceImplTests {

    @Mock
    private AuctionRepository auctionRepository;
    private AuctionServiceImpl serviceImpl;

    @BeforeEach
    public void init() {
	MockitoAnnotations.initMocks(this);
	serviceImpl = new AuctionServiceImpl(auctionRepository);
    }

    @Test
    public void getRunningAuction() {
	Mockito.when(auctionRepository.getByAuctionStatus(Mockito.anyString(), Mockito.any())).thenReturn(response("runing", getPageable()));
	Page<AuctionResponse> responses = serviceImpl.getAuctionByStatus("Running", getPageable());
	assertEquals(3, responses.getContent().size());
	Mockito.verify(auctionRepository, times(1)).getByAuctionStatus(Mockito.anyString(), Mockito.any());
	Mockito.verify(auctionRepository, times(0)).findAll();
    }

    @Test
    public void getAllAuction() {
	Page<Auction> auctions = response("Running", getPageable());
	Mockito.when(auctionRepository.findAll(Mockito.any())).thenReturn(auctions);
	Page<AuctionResponse> responses = serviceImpl.getAuctionByStatus(null, getPageable());
	assertEquals(3, responses.getContent().size());
	Mockito.verify(auctionRepository, times(0)).getByAuctionStatus(Mockito.anyString(), Mockito.any());
	Mockito.verify(auctionRepository, times(1)).findAll(Mockito.any());
    }

    private Page<Auction> response(String status, Pageable pageable) {

	ItemDetails itemDetails = new ItemDetails();
	itemDetails.setId(1);
	List<Auction> auctions = new ArrayList<>();
	Auction auction = new Auction();
	auction.setId(1);
	auction.setAuctionStatus(status);
	auction.setHighestBid(1500);
	auction.setMinPrice(1000);
	auction.setStepRate(100);
	auction.setItem(itemDetails);
	auctions.add(auction);

	Auction auction1 = new Auction();
	auction1.setId(1);
	auction1.setAuctionStatus(status);
	auction1.setHighestBid(12000);
	auction1.setMinPrice(12000);
	auction1.setStepRate(1000);
	auction1.setItem(itemDetails);
	auctions.add(auction1);

	Auction auction2 = new Auction();
	auction2.setId(1);
	auction2.setAuctionStatus(status);
	auction2.setHighestBid(20000);
	auction2.setMinPrice(10000);
	auction2.setStepRate(5000);
	auction2.setItem(itemDetails);
	auctions.add(auction2);

		return new PageImpl<Auction>(auctions, pageable, 1000);
    }

	private Pageable getPageable(){
		return PageRequest.of(0, 20, Sort.by(Sort.Order.asc("itemCode")));
	}

}
