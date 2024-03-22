package com.bookmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookmanagement.entity.UserDetails;
import com.bookmanagement.repo.UserDetailsRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDetailsRepo userDetailsRepo;

	@Override
	public List<UserDetails> getAllUserDetails() {
		//
		return userDetailsRepo.findAll();
	}

	
	
	public void addUserDetails(UserDetails newUserDetails) {
		
	
		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String hashedPassword = passwordEncoder.encode(newUserDetails.getPassword());
	        newUserDetails.setPassword(hashedPassword);
	        userDetailsRepo.save(newUserDetails);
	}
	@Override
	public UserDetails getUserDetailsById(int userId) {
		// TODO Auto-generated method stub
		Optional<UserDetails> currentUser = userDetailsRepo.findById(userId);

		if (currentUser.isPresent()) {
			return currentUser.get();
		} else {
			return null;
		}
	}

	@Override
	  
	    public ResponseEntity<UserDetails> login(String email, String password) {
	        UserDetails user = userDetailsRepo.findByEmail(email);

	        if (user != null) {
	            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	            if (passwordEncoder.matches(password, user.getPassword())) {
	                return  new ResponseEntity<UserDetails>(user, HttpStatusCode.valueOf(200));
	            }
	        }
	        System.out.println("Wrong password");
	        return new ResponseEntity<UserDetails>(HttpStatusCode.valueOf(404));
	    }
	

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		Optional<UserDetails> currentUser = userDetailsRepo.findById(userId);

		if (currentUser.isPresent()) {
			userDetailsRepo.delete(currentUser.get());
		} else {
			System.out.println(" User not found");
		}
	}

	@Override
	public void updateUser(int userId,UserDetails updateUserDetails) {
		// TODO Auto-generated method stub
		Optional<UserDetails> currentUser = userDetailsRepo.findById(userId);

		if (currentUser.isPresent()) {
			UserDetails existingUser = currentUser.get();
			existingUser.setEmail(updateUserDetails.getEmail());
			existingUser.setFirstName(updateUserDetails.getFirstName());
			existingUser.setLastName(updateUserDetails.getLastName());
			existingUser.setPassword(updateUserDetails.getPassword());
			existingUser.setRole(updateUserDetails.getRole());
			userDetailsRepo.save(existingUser);
		} else {
			System.out.println(" User not found");
		}

	}
}
