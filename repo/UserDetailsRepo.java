package com.bookmanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookmanagement.entity.UserDetails;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer> {
	
	  @Query("SELECT u FROM UserDetails u WHERE u.email = :email AND u.password = :password")
	   UserDetails loginUser(@Param("email") String email, @Param("password") String password);

	UserDetails findByEmail(String email);

}
