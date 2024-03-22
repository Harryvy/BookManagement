package com.bookmanagement.service;

import java.util.List;
import java.util.Optional;

import com.bookmanagement.entity.BookStore;

public interface BookStoreService  {
	
	void addBookStore(BookStore bookStore);

	List<BookStore> getAllBookStores();

	void updateBookStore(BookStore bookStore);

	void deleteBookStore(BookStore bookStore);

	Optional<BookStore> getBookStoreByStoreId(int storeId);

}
