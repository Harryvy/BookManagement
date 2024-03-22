package com.bookmanagement.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagement.entity.UserDetails;
import com.bookmanagement.service.UserDetailsService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UserDetailsController {

	@Autowired
	UserDetailsService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<UserDetails> login(@RequestBody Map<String, String> loginMap) {
		return userDetailsService.login(loginMap.get("email"), loginMap.get("password"));
	}

	@GetMapping("/getallusers")
	public List<UserDetails> getAllUserDetails() {
		return userDetailsService.getAllUserDetails();

	}

	@GetMapping("/getuserbyuserid/{userId}")
	public UserDetails getUserDetailsById(@PathVariable int userId) {
		return userDetailsService.getUserDetailsById(userId);
	}

	@PostMapping("/adduser")
	public void addUser(@RequestBody UserDetails newUserDetails) {
		userDetailsService.addUserDetails(newUserDetails);
	}

	@PutMapping("/updateuser/{userId}")
	public void updateUser(@PathVariable int userId, @RequestBody UserDetails updatedUserDetails) {
		userDetailsService.updateUser(userId, updatedUserDetails);
	}

	@DeleteMapping("/deleteuser/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userDetailsService.deleteUser(userId);
	}

}
