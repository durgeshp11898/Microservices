package com.learn.rating.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learn.rating.entity.Rating;

public interface RatingRepository extends MongoRepository<Rating, String>{

	//Custom Finder Methods
	
	List<Rating> findByUserId(String userId);
	
	List<Rating> findByHotelId(String hotelId);
}
