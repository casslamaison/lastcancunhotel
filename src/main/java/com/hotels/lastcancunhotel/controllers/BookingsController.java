package com.hotels.lastcancunhotel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotels.lastcancunhotel.dtos.BookingRequestDTO;
import com.hotels.lastcancunhotel.entities.BookEntity;
import com.hotels.lastcancunhotel.services.BookingService;

@RestController
@RequestMapping("v1/booking")
public class BookingsController {

	@Autowired
	private BookingService bookingService;
	
	@PostMapping
	public BookEntity bookRoom(@RequestBody BookingRequestDTO request) {
		return bookingService.bookRoom(request);
	}
	
	@GetMapping
	public ResponseEntity<Object> getAllBookings(){
		return ResponseEntity.ok().body(bookingService.getAllBookings());
	}
}
