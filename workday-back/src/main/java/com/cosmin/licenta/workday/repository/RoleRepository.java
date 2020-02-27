package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.Role;
import com.cosmin.licenta.workday.util.RoleTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleTypeEnum name);
}
