package com.learn.user.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learn.user.entity.Hotel;

@FeignClient("HOTEL-SERVICE")
public interface HotelService {

	@GetMapping("/hotels/{hotelId}")
	public Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
