package com.cars24.assignment.repository;

import com.cars24.assignment.model.jpa.BiddingDetails;
import org.springframework.data.repository.CrudRepository;

public interface BiddingRepository extends CrudRepository<BiddingDetails, Integer> {

}
