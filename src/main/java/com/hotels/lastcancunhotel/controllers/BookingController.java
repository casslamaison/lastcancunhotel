package com.hotels.lastcancunhotel.controllers;

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
import com.hotels.lastcancunhotel.services.BookingService;

@RestController
@RequestMapping("v1/booking")
public class BookingController {

	private BookingService bookingService;
	
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}
	
	@PostMapping
	public BookEntity bookRoom(@RequestBody BookingRequestDTO request) {
		return bookingService.bookRoom(request);
	}
	
	@PutMapping("/{id}")
	public BookEntity changeBooking (@PathVariable String id, @RequestBody BookingRequestDTO request) {
		return bookingService.modifyBooking(request.toBuilder().id(id).build());
	}
	
	@GetMapping
	public ResponseEntity<Object> getAllBookings(){
		return ResponseEntity.ok()
				.body(bookingService.listBookings());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> cancelBooking(@PathVariable String id){
		bookingService.cancelBooking(id);
		return ResponseEntity.noContent().build();
	}
}
