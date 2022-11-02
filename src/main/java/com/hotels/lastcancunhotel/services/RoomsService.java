package com.hotels.lastcancunhotel.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hotels.lastcancunhotel.dtos.AddRoomRequestDTO;
import com.hotels.lastcancunhotel.dtos.RoomDTO;
import com.hotels.lastcancunhotel.dtos.RoomRequestDTO;
import com.hotels.lastcancunhotel.entities.RoomEntity;
import com.hotels.lastcancunhotel.repositories.RoomsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoomsService {

	private RoomsRepository roomsRepository;
//	private BookingService bookingService;
	private ModelMapper modelMapper;
	
	public RoomsService(RoomsRepository roomsRepository, ModelMapper modelMapper) {
		this.roomsRepository = roomsRepository;
//		this.bookingService = bookingService;
		this.modelMapper = modelMapper;
	}
	
	public RoomEntity findRoomEntityById(String id) {
		return roomsRepository.findById(id)
				.orElseThrow();
	}
	
	public List<RoomDTO> getAvailableRooms(RoomRequestDTO request) {
		log.info("getAvailableRooms - Input '{}'", request);
		
		List<RoomEntity> roomEntities = roomsRepository.findAll();
		
//		bookService.getRoomAvailability(request);
		
		List<RoomDTO> rooms = roomEntities.stream()
			.map(entity -> modelMapper.map(entity, RoomDTO.class))
			.collect(Collectors.toList());
		
		log.info("getAvailableRooms - Output '{}'", rooms);
		return rooms;
		
	}
	
	public AddRoomRequestDTO addRoom(AddRoomRequestDTO request) {
		log.info("addRoom - Input '{}'", request);
		
		AddRoomRequestDTO room = modelMapper.map(
				roomsRepository.insert(modelMapper.map(request, RoomEntity.class)), 
				AddRoomRequestDTO.class
			);
		
		log.info("addRoom - Output '{}'", room);
		return room;
	}
	
	public void deleteRoom(String id) {
		roomsRepository.deleteById(id);
	}
}
