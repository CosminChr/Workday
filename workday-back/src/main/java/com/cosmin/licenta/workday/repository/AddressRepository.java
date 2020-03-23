package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.Address;
import com.cosmin.licenta.workday.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<List<Address>> findByEmployee(final Employee employee);
}
