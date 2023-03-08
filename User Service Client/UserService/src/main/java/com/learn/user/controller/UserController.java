package com.learn.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.user.entity.User;
import com.learn.user.service.UserService;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	private Logger logger =LoggerFactory.getLogger(UserController.class);

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User savedUser = this.userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
	int retry=1;
	@GetMapping("/{userId}")
	//@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallbackMethod")
	//@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallbackMethod")
	@RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallbackMethod")
	public ResponseEntity<User> getSingleUser(@PathVariable("userId") String userId){
		logger.info("Get Single User Handler : UserController");
		retry++;
		logger.info("Retry Count {} "+retry);
		User user = this.userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	
	
	public ResponseEntity<User> ratingHotelFallbackMethod(String userId,Exception ex){
		logger.info("fallback is executed due to service is down",ex.getMessage());
		User user = User.builder()
		.aboutUser("This user is created DUMMY because some services are down")
		.userEmail("dummy@gmail.com")
		.userName("Dummy")
		.userId("0000")
		.build();
		
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		 List<User> user = this.userService.getAllUsers();
		return ResponseEntity.ok(user);
		
	}
	
	
	
	
	
}
