package com.bookmanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmanagement.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
	
	List<Book>  findByBookName(String bookName);

}
