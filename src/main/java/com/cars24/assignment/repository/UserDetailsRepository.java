package com.cars24.assignment.repository;

import com.cars24.assignment.model.jpa.UserDetails;
import org.springframework.data.repository.CrudRepository;

public interface UserDetailsRepository extends CrudRepository<UserDetails, String> {

  UserDetails findByEmail(String email);
}
