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

	RoomsRepository roomsRepository;
	
	ModelMapper modelMapper;
	
	public RoomsService(RoomsRepository roomsRepository, ModelMapper modelMapper) {
		this.roomsRepository = roomsRepository;
		this.modelMapper = modelMapper;
	}
	
	public List<RoomDTO> getAvailableRooms(RoomRequestDTO request) {
		log.info("getAvailableRooms - Input '{}'", request);
		
		List<RoomEntity> roomEntities = roomsRepository.findAll();
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
	
}
