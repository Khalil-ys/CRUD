package com.onetomany.repository;

import com.onetomany.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item,Integer> {
}
