 package com.bookmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmanagement.entity.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {

}
