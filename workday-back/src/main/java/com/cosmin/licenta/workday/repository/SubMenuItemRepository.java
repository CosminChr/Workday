package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.SubMenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubMenuItemRepository extends JpaRepository<SubMenuItem, Long> {
}
