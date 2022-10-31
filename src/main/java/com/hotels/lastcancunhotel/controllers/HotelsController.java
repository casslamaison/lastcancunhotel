package com.hotels.lastcancunhotel.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotels.lastcancunhotel.dtos.HotelRequestDTO;
import com.hotels.lastcancunhotel.services.HotelsService;

@RestController
@RequestMapping("v1/hotels")
public class HotelsController {

	HotelsService hotelsService;
	
	@Autowired
	public HotelsController(HotelsService hotelsService) {
		this.hotelsService = hotelsService;
	}
	
	@GetMapping
	public ResponseEntity<Object> getHotels(){
		return ResponseEntity.ok().build();
		
	}
	
	@GetMapping("/availability")
	public ResponseEntity<Object> getRoomsAvailabilty(@Valid HotelRequestDTO request) {
		return ResponseEntity.ok().build();
	}
}
