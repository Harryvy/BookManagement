package com.bookmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmanagement.entity.Book;
import com.bookmanagement.entity.OrderItem;
import com.bookmanagement.entity.OrderItemRequest;
import com.bookmanagement.entity.OrderStatus;
import com.bookmanagement.entity.Orders;
import com.bookmanagement.entity.UserDetails;
import com.bookmanagement.repo.BookRepo;
import com.bookmanagement.repo.OrderItemRepo;
import com.bookmanagement.repo.OrdersRepo;
import com.bookmanagement.repo.UserDetailsRepo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrderItemRepo orderItemRepo;

	@Autowired
	private OrdersRepo ordersRepo;

	@Autowired
	private UserDetailsRepo userDetailsRepository;

	@Autowired
	private BookRepo bookRepository;

	@Override
	@Transactional
	public Orders placeOrder(int userId, List<OrderItemRequest> orderItems) {
		UserDetails user = userDetailsRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

		Orders order = new Orders();
		order.setUser(user);
		order.setOrderStatus(OrderStatus.PLACED);

		// Save the order to generate the order ID
		ordersRepo.save(order);

		for (OrderItemRequest orderItemRequest : orderItems) {
			Optional<Book> presentBookOpt = bookRepository.findById(orderItemRequest.getBookId());

			if (presentBookOpt.isPresent()) {
				Book presentBook = presentBookOpt.get();

				// Check if there are enough available quantities
				if (presentBook.getQuantity() >= orderItemRequest.getQuantity()) {
					// Update the book quantity
					presentBook.setQuantity(presentBook.getQuantity() - orderItemRequest.getQuantity());
					bookRepository.save(presentBook);

					// Create and save the order item
					OrderItem orderItemNew = new OrderItem();
					orderItemNew.setOrder(order);
					orderItemNew.setBook(presentBook);
					orderItemNew.setQuantity(orderItemRequest.getQuantity());
					orderItemRepo.save(orderItemNew);
					order.getOrderItems().add(orderItemNew);
				} else {
					// Handle insufficient quantity (you may throw an exception or log a message)
					System.out.println("Insufficient quantity for book with ID: " + presentBook.getBookId());
				}
			} else {
				// Handle book not found
				System.out.println("Book not present with ID: " + orderItemRequest.getBookId());
			}
		}

		// Save the order again (after updating book quantities)
		ordersRepo.save(order);

		Orders orderWithItems = ordersRepo.findById(order.getOrderId())
				.orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + order.getOrderId()));

		return orderWithItems;
//	    return order;
	}

	@Override
	public List<Orders> getAllOrders() {
		return ordersRepo.findAll();
	}
	
	@Override
	public List<Orders> getOrdersOfUser(int userId) {
		return ordersRepo.findByUser_UserId(userId);
	}

	@Override
	public void cancelOrder(int orderId) {
		Optional<Orders> currentOrder = ordersRepo.findById(orderId);
		if (currentOrder.isPresent()) {
			currentOrder.get().setOrderStatus(OrderStatus.CANCELLED);
			ordersRepo.save(currentOrder.get());
		} else {
			System.out.println("Order Not found");
		}

	}
}