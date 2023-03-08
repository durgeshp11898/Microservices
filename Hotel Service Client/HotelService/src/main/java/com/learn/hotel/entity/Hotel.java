package com.learn.hotel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "micro_hotel")
public class Hotel {

	@Id
	private String hotelId;
	
	private String hotelName;
	
	private String hotelLocation;
	
	private String aboutHotel;
	
}
