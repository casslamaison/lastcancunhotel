package com.hotels.lastcancunhotel.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotels.lastcancunhotel.dtos.AddRoomRequestDTO;
import com.hotels.lastcancunhotel.dtos.RoomRequestDTO;
import com.hotels.lastcancunhotel.services.RoomsService;

@RestController
@RequestMapping("v1/rooms")
public class RoomsController {

	RoomsService roomsService;
	
	public RoomsController(RoomsService roomsService) {
		this.roomsService = roomsService;
	}
	
	@GetMapping("/availability")
	public ResponseEntity<Object> getAvailability(@Valid RoomRequestDTO request) {
		return ResponseEntity.ok()
				.body(roomsService.getAvailableRooms(request));
	}
	
	@PostMapping
	public ResponseEntity<Object> addRoom(@RequestBody AddRoomRequestDTO request) {
		return ResponseEntity.ok()
				.body(roomsService.addRoom(request));
	}
}
