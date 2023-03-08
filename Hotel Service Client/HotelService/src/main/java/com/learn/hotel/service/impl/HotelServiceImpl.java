package com.learn.hotel.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.hotel.Repository.HotelRepository;
import com.learn.hotel.entity.Hotel;
import com.learn.hotel.exception.ResourceNotFoundException;
import com.learn.hotel.service.HotelService;



@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	
	@Override
	public Hotel createHotel(Hotel hotel) {
		String randomUserId = UUID.randomUUID().toString();
		hotel.setHotelId(randomUserId);
		return this.hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotels() {
		return this.hotelRepository.findAll();
	}

	@Override
	public Hotel getHotel(String hotelId) {
		Hotel hotel = this.hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Resource Not Found on a Server with userID "+hotelId));
		return hotel;
	}

	@Override
	public void deleteHotel(String hotelId) {
		Hotel hotel = this.hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Resource Not Found on a Server with userID "+hotelId));
		this.hotelRepository.delete(hotel);
	}

	@Override
	public Hotel updateHotel(Hotel hotel, String hotelId) {
		// TODO Auto-generated method stub
		return null;
	}

}
