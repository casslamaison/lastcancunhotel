package com.hotels.lastcancunhotel.services;

import java.util.Collections;
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
	
	public List<RoomDTO> getAvailableRooms(RoomRequestDTO request) {
		log.info("getAvailableRooms - Input '{}'", request);
		
		List<RoomEntity> roomEntities = roomsRepository.findAll();
		
		List<BookEntity> bookedRooms = bookingService.getBookedRooms(request.getCheckIn(), request.getCheckOut());
		if(bookedRooms.isEmpty()) {
			log.info("Não deixa bookar {}", bookedRooms);
			return Collections.emptyList();
		}
		
		List<RoomDTO> rooms = roomEntities.stream()
			.map(entity -> modelMapper.map(entity, RoomDTO.class))
			.collect(Collectors.toList());
		
		log.info("getAvailableRooms - Output '{}'", rooms);
		return rooms;
	}
	
}
