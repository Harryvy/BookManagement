package com.bookmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmanagement.entity.Book;
import com.bookmanagement.entity.BookStore;
import com.bookmanagement.repo.BookRepo;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepo bookRepo;

	@Override
	public void insertBook(Book b) {

		bookRepo.save(b);

	}

	@Override
	public List<Book> findAllBooks() {
		// TODO Auto-generated method stub
		return bookRepo.findAll();
	}
	@Override
	public Optional<Book> findBookByBookId(int bookId) {
		return bookRepo.findById(bookId);
	}
	@Override
	public void updateBookDetails(Book b) {
		 bookRepo.save(b);
	}
	
	@Override
	public void deleteBook(Book book) {
		bookRepo.delete(book);
	}

}
