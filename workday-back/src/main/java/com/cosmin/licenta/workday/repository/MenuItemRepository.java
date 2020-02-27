package com.cosmin.licenta.workday.repository;

import com.cosmin.licenta.workday.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
