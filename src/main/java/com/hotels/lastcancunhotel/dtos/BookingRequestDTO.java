package com.hotels.lastcancunhotel.dtos;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder=true)
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {

	private String id;
	private String roomId;
	@Future
	@NotNull
	private Date checkIn;
	@Future
	@NotNull
	private Date checkOut;
	
}
