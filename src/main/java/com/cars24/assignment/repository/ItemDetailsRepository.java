package com.cars24.assignment.repository;

import com.cars24.assignment.model.jpa.ItemDetails;
import org.springframework.data.repository.CrudRepository;

public interface ItemDetailsRepository extends CrudRepository<ItemDetails, Integer> {

  ItemDetails getById(int id);
}
