package com.bookmanagement.entity;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "book_store")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BookStore {

	@Id
	@Column(name = "store_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storeId;

	@JsonManagedReference
	@OneToMany(mappedBy = "bookStore", cascade = CascadeType.ALL)
	private List<Book> books;

	@Column(name = "store_name")
	private String storeName;

	@Column(name = "store_address")
	private String storeAddress;

}
