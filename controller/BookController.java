package com.bookmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagement.entity.Book;
import com.bookmanagement.entity.BookStore;
import com.bookmanagement.service.BookService;
//
//@RestController
//@RequestMapping("/api/v1")
//public class BookController {
//
//	@Autowired
//	BookService bookService;
//
//	@GetMapping("/getbooks")
//	public List<Book> getAllBooks() {
//		System.out.println("inside here");
//		return bookService.findAllBooks();
//	}
//	
//	@GetMapping("/getbookbybookid")
//	public ResponseEntity<Book>  getBookByBookId(@PathVariable int bookId) {
//	
//		Optional<Book> book = bookService.findBookByBookId(bookId);
//		if(book.isPresent()) {
//			return new ResponseEntity<Book>(book.get(), HttpStatus.OK);
//		}
//		else {
//			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@PostMapping("/addbook")
//	public void insertBook(@RequestBody Book book) {
//		bookService.insertBook(book);
//	}
//	
//	@PutMapping("/updatebook/{bookId}")
//	public void updateBookStore(@PathVariable int bookId, @RequestBody Book updatedBook) {
//		Optional<Book> matchingBook = bookService.findAllBooks().stream()
//				.filter(item -> item.getBookId() == bookId).findFirst();
//
//		if (matchingBook.isPresent()) {
//			// Get the matching BookStore
//			Book existingBook = matchingBook.get();
//
//			// Update the BookStore attributes with the values from the updatedBookStore
//			existingBook.setBookName(updatedBook.getBookName());
//			existingBook.setBookPrice(updatedBook.getBookPrice());
//			existingBook.setQuantity(updatedBook.getQuantity());
//			existingBook.setBookStore(updatedBook.getBookStore());
//
//			bookService.updateBookDetails(existingBook);
//		} else {
//			System.out.println("error store not found");
//			// Handle the case where the BookStore with the given ID is not found
//			// You may choose to throw an exception, return an error response, etc.
//		}
//	}
//	@DeleteMapping("/deletebook/{bookId}")
//	public void deleteBookStore(@PathVariable int bookId) {
//		Optional<Book> matchingBook = bookService.findAllBooks().stream()
//				.filter(item -> item.getBookId() == bookId).findFirst();
//
//		if (matchingBook.isPresent()) {
//			// Get the matching BookStore
//			Book existingBook = matchingBook.get();
//
//			bookService.deleteBook(existingBook);
//		} else {
//			System.out.println("error store not found");
//			// Handle the case where the BookStore with the given ID is not found
//			// You may choose to throw an exception, return an error response, etc.
//		}
//	}
//
//	@GetMapping("/testapi")
//	public String testApi() {
//		return "API IS WORKING FINE";
//	}
//
//}

@RestController
@RequestMapping("/api/v1")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/getbooks")
	public List<Book> getAllBooks() {
		return bookService.findAllBooks();
	}

	@GetMapping("/getbook/{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable int bookId) {
		return bookService.findBookByBookId(bookId).map(book -> new ResponseEntity<>(book, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/addbook")
	public ResponseEntity<Void> insertBook(@RequestBody Book book) {
		bookService.insertBook(book);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/updatebook/{bookId}")
	public ResponseEntity<Void> updateBook(@PathVariable int bookId, @RequestBody Book updatedBook) {
		return bookService.findBookByBookId(bookId).map(existingBook -> {
			existingBook.setBookName(updatedBook.getBookName());
			existingBook.setBookPrice(updatedBook.getBookPrice());
			existingBook.setQuantity(updatedBook.getQuantity());
			existingBook.setBookStore(updatedBook.getBookStore());
			bookService.updateBookDetails(existingBook);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("/deletebook/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable int bookId) {
		return bookService.findBookByBookId(bookId).map(existingBook -> {
			bookService.deleteBook(existingBook);
			return ResponseEntity.noContent().<Void>build();
		}).orElseGet(() -> ResponseEntity.notFound().<Void>build());

	}

	@GetMapping("/testapi")
	public String testApi() {
		return "API IS WORKING FINE";
	}
}
