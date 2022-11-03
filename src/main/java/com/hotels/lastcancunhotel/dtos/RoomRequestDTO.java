package com.hotels.lastcancunhotel.dtos;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Valid
@Builder
public class RoomRequestDTO {

	private final String DATE_FORMAT = "yyyy-MM-dd";
	
	@Future
	@NotNull(message = "checkIn : must not be null")
	@DateTimeFormat(pattern = DATE_FORMAT)
	private Date checkIn;

	@Future
	@NotNull(message = "checkOut : must not be null")
	@DateTimeFormat(pattern = DATE_FORMAT)
	private Date checkOut;
	
}
