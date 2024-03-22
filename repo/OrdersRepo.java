package com.bookmanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmanagement.entity.Orders;
import com.bookmanagement.entity.UserDetails;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Integer> {
	
	List<Orders> findByUser_UserId(int userId);

	

}
