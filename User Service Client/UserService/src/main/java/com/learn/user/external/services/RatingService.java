package com.learn.user.external.services;



import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.learn.user.entity.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

	//CREATE
	
	@PostMapping("/ratings")
	public ResponseEntity<Rating> createRatings(Rating rating);
	
	//Update
	
	@PutMapping("/ratings/{ratingId}")
	public ResponseEntity<Rating> updateRating(Rating rating,@PathVariable("ratingId") String ratingId);
	
	
	//Delete 
	
	@DeleteMapping("/ratings/{ratingId}")
	public ResponseEntity<Rating> deleteRating(@PathVariable("ratingId") String ratingId);
	
	
	//Get All Ratings
	@GetMapping("/ratings")
	public ResponseEntity<List<Rating>> getAllRatings();
	
	
}
