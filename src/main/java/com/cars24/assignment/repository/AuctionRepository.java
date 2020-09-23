package com.cars24.assignment.repository;

import com.cars24.assignment.model.jpa.Auction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuctionRepository extends CrudRepository<Auction, Integer> {

  Auction getByItemId(int id);

  List<Auction> getByAuctionStatus(String status);

}
