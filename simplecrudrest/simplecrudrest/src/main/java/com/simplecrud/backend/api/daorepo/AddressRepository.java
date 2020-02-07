package com.simplecrud.backend.api.daorepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplecrud.backend.api.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
