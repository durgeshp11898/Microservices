package com.learn.rating.service;

import java.util.List;

import com.learn.rating.entity.Rating;

public interface RatingService {

	//Create Rating
	Rating createRating(Rating rating);
	
	//Get All Ratings
	List<Rating> getAllRatings();
	
	//get all by user
	List<Rating> getRatingByUserId(String userId);
	
	//get all by Hotels
	
	List<Rating> getRatingByHotelId(String hotelId);
}
