package com.hotels.lastcancunhotel.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotels.lastcancunhotel.dtos.BookingRequestDTO;
import com.hotels.lastcancunhotel.entities.BookEntity;
import com.hotels.lastcancunhotel.services.booking.Booking;

@RestController
@RequestMapping("v1/booking")
public class BookingController {

	private Booking bookingService;
	
	public BookingController(Booking bookingService) {
		this.bookingService = bookingService;
	}
	
	@PostMapping
	public ResponseEntity<Object> bookRoom(@RequestBody @Valid BookingRequestDTO request) {
		return new ResponseEntity<Object>(bookingService.book(request), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public BookEntity changeBooking (@PathVariable String id, @RequestBody @Valid BookingRequestDTO request) {
		return bookingService.modify(request.toBuilder().id(id).build());
	}
	
	@GetMapping
	public ResponseEntity<Object> getAllBookings(){
		return ResponseEntity.ok()
			.body(bookingService.list());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> cancelBooking(@PathVariable String id){
		bookingService.cancel(id);
		return ResponseEntity.noContent().build();
	}
}
