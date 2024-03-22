package com.bookmanagement.service;

import java.util.List;

import com.bookmanagement.entity.Book;
import com.bookmanagement.entity.OrderItem;
import com.bookmanagement.entity.OrderItemRequest;
import com.bookmanagement.entity.Orders;
import com.bookmanagement.entity.UserDetails;

public interface OrdersService {
	
	

	Orders placeOrder(int userId, List<OrderItemRequest> orderItem);

	List<Orders> getAllOrders();

	void cancelOrder(int orderId);

	List<Orders> getOrdersOfUser(int userId);

}
