package com.bookmanagement.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagement.entity.Book;
import com.bookmanagement.entity.BookStore;
import com.bookmanagement.service.BookService;
import com.bookmanagement.service.BookStoreService;

@RestController
@RequestMapping("/api/v1")
public class BookStoreController {

	@Autowired
	BookStoreService bookStoreService;

	@GetMapping("/getbookstorebyid/{bookStoreId}")
	public ResponseEntity<BookStore> getBookStoreById(@PathVariable int bookStoreId) {
		Optional<BookStore> bookStorePresent = bookStoreService.getBookStoreByStoreId(bookStoreId);
		if (bookStorePresent.isPresent()) {
			return new ResponseEntity<BookStore>(bookStorePresent.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<BookStore>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getallbookstores")
	public List<BookStore> getAllBookStores() {
		return bookStoreService.getAllBookStores();
	}

	@PostMapping("/addbookstore")
	public void addBookStore(@RequestBody BookStore bookStore) {
		bookStoreService.addBookStore(bookStore);
	}

	@PutMapping("/updatebookstore/{bookStoreId}")
	public void updateBookStore(@PathVariable int bookStoreId, @RequestBody BookStore updatedBookStore) {
		Optional<BookStore> matchingBookStore = bookStoreService.getAllBookStores().stream()
				.filter(item -> item.getStoreId() == bookStoreId).findFirst();

		if (matchingBookStore.isPresent()) {
			// Get the matching BookStore
			BookStore existingBookStore = matchingBookStore.get();

			// Update the BookStore attributes with the values from the updatedBookStore
			existingBookStore.setStoreName(updatedBookStore.getStoreName());

			bookStoreService.updateBookStore(existingBookStore);
		} else {
			System.out.println("error store not found");
			// Handle the case where the BookStore with the given ID is not found
			// You may choose to throw an exception, return an error response, etc.
		}
	}

	@DeleteMapping("/deletebookstore/{bookStoreId}")
	public void deleteBookStore(@PathVariable int bookStoreId) {
		Optional<BookStore> matchingBookStore = bookStoreService.getAllBookStores().stream()
				.filter(item -> item.getStoreId() == bookStoreId).findFirst();

		if (matchingBookStore.isPresent()) {
			// Get the matching BookStore
			BookStore existingBookStore = matchingBookStore.get();

			bookStoreService.deleteBookStore(existingBookStore);
		} else {
			System.out.println("error store not found");
			// Handle the case where the BookStore with the given ID is not found
			// You may choose to throw an exception, return an error response, etc.
		}
	}
}
