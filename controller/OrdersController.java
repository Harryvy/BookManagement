package com.bookmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagement.entity.OrderItem;
import com.bookmanagement.entity.OrderItemRequest;
import com.bookmanagement.entity.Orders;
import com.bookmanagement.service.OrdersService;

@RestController
@RequestMapping("/api/v1")
public class OrdersController {

	@Autowired
	OrdersService ordersService;
	
	@PostMapping("/placeorder/{userId}")
	public Orders placeOrder(@PathVariable int userId,@RequestBody List<OrderItemRequest> orderItem) {
		return ordersService.placeOrder(userId, orderItem);
	}
	
	@PutMapping("/cancelorder/{orderId}")
	public void placeOrder(@PathVariable int orderId) {
		 ordersService.cancelOrder(orderId);
	}
	
	@GetMapping("/allorders")
	public List<Orders> getAllOrders() {
		return ordersService.getAllOrders();
	}
	
	@GetMapping("/getorders/{userId}")
	public List<Orders> getOrdersWithUser(@PathVariable int userId) {
		return ordersService.getOrdersOfUser(userId);
	}
}
