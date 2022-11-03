package com.hotels.lastcancunhotel.services;

import java.util.List;
import java.util.UUID;
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
	private ModelMapper modelMapper;
	
	public RoomsService(RoomsRepository roomsRepository, ModelMapper modelMapper) {
		this.roomsRepository = roomsRepository;
		this.modelMapper = modelMapper;
	}
	
	public RoomEntity findRoomEntityById(String id) {
		return roomsRepository.findById(id)
				.orElseThrow();
	}
	
	public List<RoomDTO> listRooms(){
		return roomsRepository.findAll().stream()
				.map(entity -> modelMapper.map(entity, RoomDTO.class))
				.collect(Collectors.toList());
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
			roomsRepository.insert(modelMapper.map(request.toBuilder()
				.id(UUID.randomUUID().toString())
				.build(), RoomEntity.class)), 
			AddRoomRequestDTO.class
		);
		
		log.info("addRoom - Output '{}'", room);
		return room;
	}
	
	public void deleteRoom(String id) {
		log.info("Deleting room '{}'", id);
		roomsRepository.deleteById(id);
	}
}
