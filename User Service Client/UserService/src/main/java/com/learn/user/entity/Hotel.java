package com.learn.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

	private String hotelId;
	private String hotelName;
	private String hotelLocation;
	private String aboutHotel;
}
