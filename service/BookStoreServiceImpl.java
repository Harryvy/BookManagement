package com.bookmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmanagement.entity.BookStore;
import com.bookmanagement.repo.BookStoreRepo;

@Service
public class BookStoreServiceImpl implements BookStoreService {

	@Autowired
	BookStoreRepo bookStoreRepo;

	@Override
	public void addBookStore(BookStore bookStore) {
		bookStoreRepo.save(bookStore);

	}

	@Override
	public List<BookStore> getAllBookStores() {
		return bookStoreRepo.findAll();

	}

	@Override
	public void updateBookStore(BookStore bookStore) {
		bookStoreRepo.save(bookStore);
	}

	@Override
	public void deleteBookStore(BookStore bookStore) {
		bookStoreRepo.delete(bookStore);
	}
	
	@Override
	public Optional<BookStore> getBookStoreByStoreId(int storeId) {
		return bookStoreRepo.findById(storeId);
	}

	
}
