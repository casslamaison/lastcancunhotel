package com.hotels.lastcancunhotel.services.booking;

import java.util.Date;
import java.util.List;

import com.hotels.lastcancunhotel.dtos.BookingRequestDTO;
import com.hotels.lastcancunhotel.dtos.BookingResponseDTO;
import com.hotels.lastcancunhotel.entities.BookEntity;

public  interface Booking {

	public List<BookEntity> listWithinRange(Date checkIn, Date checkOut);
	
	public void cancel(String id);
	
	public List<BookingResponseDTO> list();
	
	public BookEntity book(BookingRequestDTO request);
	
	public BookEntity modify(BookingRequestDTO request);
	
}
