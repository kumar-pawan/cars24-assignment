package com.cars24.assignment.repository;

import com.cars24.assignment.model.jpa.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface AuctionRepository extends CrudRepository<Auction, Integer> {

  Auction getByItemId(int id);

  Page<Auction> getByAuctionStatus(String status, Pageable pageable);

  Page<Auction> findAll(Pageable pageable);

}
