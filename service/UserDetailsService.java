package com.bookmanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bookmanagement.entity.UserDetails;

public interface UserDetailsService {

	List<UserDetails> getAllUserDetails();

	UserDetails getUserDetailsById(int userId);

	ResponseEntity<UserDetails> login (String email,String password);

	void deleteUser(int userId);
	
	 void addUserDetails(UserDetails newUserDetails);

	void updateUser(int userId, UserDetails updateUserDetails);
}
