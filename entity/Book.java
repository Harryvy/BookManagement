package com.bookmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "book")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Book {

	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int bookId;

	@Column(name = "book_price")
	float bookPrice;

	@Column(name = "book_name")
	String bookName;

	@ManyToOne
	@JoinColumn(name = "store_id")
	@JsonBackReference
	private BookStore bookStore;

	@Column(name = "quantity")
	private int quantity;
	
	

	

	

}
