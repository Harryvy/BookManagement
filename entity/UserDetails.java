package com.bookmanagement.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "userdetails")
public class UserDetails {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userId;

	@Column(name = "first_name")
	String firstName;

	@Column(name = "last_name")
	String lastName;

	@Column(name = "email")
	String email;

	@Column(name = "password")
	String password;

	@Column(name = "role")
	Role role;

	
//	   public void setPassword(String password) {
//	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//	        this.password = passwordEncoder.encode(password);
//	    }
}
