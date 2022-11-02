package com.hotels.lastcancunhotel.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {

	private String id;
	private String roomId;
	private Date checkIn;
	private Date checkOut;
	
}
