package com.hotels.lastcancunhotel.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotels.lastcancunhotel.dtos.AddRoomRequestDTO;
import com.hotels.lastcancunhotel.dtos.RoomRequestDTO;
import com.hotels.lastcancunhotel.services.booking.Booking;
import com.hotels.lastcancunhotel.services.room.Room;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/rooms")
public class RoomsController {

	private final Room roomsService;
	private final Booking bookingService;
	
	@GetMapping
	public ResponseEntity<Object> listRooms() {
		return ResponseEntity.ok()
			.body(roomsService.listAll());
	}
	
	@GetMapping("/available")
	public ResponseEntity<Object> listAvailableRooms(@Valid RoomRequestDTO request) {
		return ResponseEntity.ok()
			.body(bookingService.listAvailable(request));
	}
	
	@PostMapping
	public ResponseEntity<Object> addRoom(@RequestBody AddRoomRequestDTO request) {
		return ResponseEntity.ok()
			.body(roomsService.add(request));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteRoom(@PathVariable String id) {
		roomsService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
