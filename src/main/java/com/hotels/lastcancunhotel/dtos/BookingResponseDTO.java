package com.hotels.lastcancunhotel.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {

	private String id;
	private Date checkIn;
	private Date checkOut;
	private String roomId;
	private String roomName;
	
}
