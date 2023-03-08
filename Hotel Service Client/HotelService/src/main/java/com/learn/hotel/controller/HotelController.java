package com.learn.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.hotel.entity.Hotel;
import com.learn.hotel.payloads.ApiResponse;
import com.learn.hotel.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		Hotel savedHotel = this.hotelService.createHotel(hotel);
		return new ResponseEntity<Hotel>(savedHotel,HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotels(){
		return ResponseEntity.ok(hotelService.getAllHotels());
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getSingleHotels(@PathVariable("hotelId") String hotelId){
		return ResponseEntity.ok(hotelService.getHotel(hotelId));
	}
	
	
	@DeleteMapping("/{hotelId}")
	public ResponseEntity<ApiResponse> deleteHotel(@PathVariable("hotelId") String hotelId){
		this.hotelService.deleteHotel(hotelId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Hotel is Deleted",true,HttpStatus.OK),HttpStatus.OK);
	}
	
	
	
}

