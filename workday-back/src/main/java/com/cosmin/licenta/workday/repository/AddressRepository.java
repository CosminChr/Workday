package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
