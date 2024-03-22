package com.bookmanagement.service;

import java.util.List;
import java.util.Optional;

import com.bookmanagement.entity.Book;

public interface BookService {
	
	List<Book> findAllBooks();
	
	void insertBook(Book b);

	void deleteBook(Book book);

	void updateBookDetails(Book b);

	Optional<Book> findBookByBookId(int bookId);

}
