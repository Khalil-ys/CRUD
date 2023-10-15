package com.onetomany.repository;

import com.onetomany.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,Integer> {
}
