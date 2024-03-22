package com.bookmanagement.entity;

public class OrderItemRequest {

	private int bookId;
	private int quantity;

	// Constructors, getters, and setters

	public OrderItemRequest() {
		// Default constructor
	}

	public OrderItemRequest(int bookId, int quantity) {
		this.bookId = bookId;
		this.quantity = quantity;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
