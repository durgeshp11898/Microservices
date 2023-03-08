package com.learn.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.hotel.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
