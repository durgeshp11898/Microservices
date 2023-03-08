package com.learn.hotel.service;

import java.util.List;

import com.learn.hotel.entity.Hotel;

public interface HotelService {

	//Create Hotel

	Hotel createHotel(Hotel hotel);

	//Get Hotels

	List<Hotel> getAllHotels();

	//Get Hotel By Id

	Hotel getHotel(String hotelId);

	void deleteHotel(String hotelId);

	//update Hotel

	Hotel updateHotel(Hotel hotel,String hotelId);
}
