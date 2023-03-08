package com.learn.user.service.impl;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learn.user.entity.Hotel;
import com.learn.user.entity.Rating;
import com.learn.user.entity.User;
import com.learn.user.exception.ResourceNotFoundException;
import com.learn.user.external.services.HotelService;

import com.learn.user.repository.UserRepository;
import com.learn.user.service.UserService;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	
	private Logger logger =LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return this.userRepository.save(user); 
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();	 
	}

	@Override
	public User getUser(String userId) {
		//Get User from database with the help of JPARepository
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Resource Not Found on a Server with userID "+userId));
		
		//Fetch rating of the above user from Rating Service
		//http://localhost:8084/ratings/users/durgeshpawar077
		
		
	
		Rating[] ratingsOfUser = this.restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(),Rating[].class);
		
		
		  List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		//Arrays.stream(ratings).toList();
		logger.info("RestTemplate Logger :"+ratings);
		
		ratings.stream().map(ratinng ->{
			//API call to hotel service to get the Hotel
			//http://localhost:8088/hotels/973c6b55-8cf1-4b8d-9f3d-9e8952030e75
			
			//ResponseEntity<Hotel> hotelEntity = this.restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+ratinng.getHotelId(),Hotel.class);
			
			//Hotel hotel = hotelEntity.getBody();
			 Hotel hotel = this.hotelService.getHotel(ratinng.getHotelId());
			
			logger.info("Response Status Code :"+ratinng);
			
			ratinng.setHotel(hotel);
			//Set hotel to rating
			//return the Rating
			
			return hotel; 
			
		}).collect(Collectors.toList());
		
		user.setRatings(ratings);
		
		return user;
	}

	@Override
	public void deleteUser(String userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Resource Not Found on a Server with userID "+userId));
		this.userRepository.delete(user); 
	}

	@Override
	public User updateUser(User user, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
