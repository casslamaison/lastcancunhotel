package com.hotels.lastcancunhotel.services.room;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hotels.lastcancunhotel.dtos.RoomDTO;
import com.hotels.lastcancunhotel.dtos.RoomRequestDTO;
import com.hotels.lastcancunhotel.entities.BookEntity;
import com.hotels.lastcancunhotel.entities.RoomEntity;
import com.hotels.lastcancunhotel.repositories.RoomsRepository;
import com.hotels.lastcancunhotel.services.booking.Booking;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoomsAvailabilityService implements RoomAvailability {

	private RoomsRepository roomsRepository;
	
	private Booking bookingService;
	
	private ModelMapper modelMapper;
	
	public RoomsAvailabilityService(RoomsRepository roomsRepository, Booking bookingService, ModelMapper modelMapper) {
		this.roomsRepository = roomsRepository;
		this.bookingService = bookingService;
		this.modelMapper = modelMapper;
	}
	
	public List<RoomDTO> listAvailable(RoomRequestDTO request) {
		log.info("listAvailableRooms - input [{}]", request);

		List<RoomEntity> roomEntities = roomsRepository.findAll();
		
		List<RoomEntity> bookedRooms = bookingService.listWithinRange(request.getCheckIn(), request.getCheckOut())
			.stream().map(BookEntity::getRoom)
			.collect(Collectors.toList());
		
		return roomEntities.stream()
			.filter(entity -> !bookedRooms.contains(entity))
			.map(entity -> modelMapper.map(entity, RoomDTO.class))
			.collect(Collectors.toList());
	}

}
