package com.hotels.lastcancunhotel.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotels.lastcancunhotel.dtos.RoomDTO;
import com.hotels.lastcancunhotel.dtos.RoomRequestDTO;
import com.hotels.lastcancunhotel.entities.BookEntity;
import com.hotels.lastcancunhotel.entities.RoomEntity;
import com.hotels.lastcancunhotel.repositories.RoomsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoomsAvailabilityService {

	@Autowired
	private RoomsRepository roomsRepository;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<RoomDTO> listAvailableRooms(RoomRequestDTO request) {
		log.info("listAvailableRooms - input [{}]", request);
		
		List<RoomEntity> roomEntities = roomsRepository.findAll();
		
		List<RoomEntity> bookedRooms = bookingService.getBookedRoomsWithinRange(request.getCheckIn(), request.getCheckOut())
			.stream().map(BookEntity::getRoom)
			.collect(Collectors.toList());
		
		return roomEntities.stream()
			.filter(entity -> !bookedRooms.contains(entity))
			.map(item -> item)
			.map(entity -> modelMapper.map(entity, RoomDTO.class))
			.collect(Collectors.toList());
	}

}
