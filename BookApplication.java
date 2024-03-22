package com.bookmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookmanagement.entity.Book;

@SpringBootApplication
public class BookApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookApplication.class, args);
		System.out.println("------------------SpringBoot Application Started----------------------");

		
	}

}