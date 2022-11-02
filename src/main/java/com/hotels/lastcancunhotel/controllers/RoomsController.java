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
import com.hotels.lastcancunhotel.services.RoomsAvailabilityService;
import com.hotels.lastcancunhotel.services.RoomsService;

@RestController
@RequestMapping("v1/rooms")
public class RoomsController {

	RoomsService roomsService;
	RoomsAvailabilityService roomsAvailabilityService;
	
	public RoomsController(RoomsService roomsService, RoomsAvailabilityService roomsAvailabilityService) {
		this.roomsService = roomsService;
		this.roomsAvailabilityService = roomsAvailabilityService;
	}
	
	@GetMapping("/availability")
	public ResponseEntity<Object> getAvailability(@Valid RoomRequestDTO request) {
		return ResponseEntity.ok()
				.body(roomsAvailabilityService.getAvailableRooms(request));
	}
	
	@PostMapping
	public ResponseEntity<Object> addRoom(@RequestBody AddRoomRequestDTO request) {
		return ResponseEntity.ok()
				.body(roomsService.addRoom(request));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteRoom(@PathVariable String id) {
		
		roomsService.deleteRoom(id);
		
		return ResponseEntity.noContent().build();
	}
}
