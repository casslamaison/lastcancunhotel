package com.hotels.lastcancunhotel.services.room;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hotels.lastcancunhotel.dtos.AddRoomRequestDTO;
import com.hotels.lastcancunhotel.dtos.RoomDTO;
import com.hotels.lastcancunhotel.entities.RoomEntity;
import com.hotels.lastcancunhotel.repositories.RoomsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
class RoomsService implements Room {

	private final RoomsRepository roomsRepository;
	private final ModelMapper modelMapper;
	
	public RoomEntity findById(String id) {
		log.info("findRoomEntityById - input [{}]", id);
		return roomsRepository.findById(id).orElseThrow();
	}
	
	public List<RoomDTO> listAll(){
		log.info("listRooms");
		return roomsRepository.findAll().stream()
			.map(entity -> modelMapper.map(entity, RoomDTO.class))
			.collect(Collectors.toList());
	}
	
	public AddRoomRequestDTO add(AddRoomRequestDTO request) {
		log.info("addRoom - input [{}]", request);
		
		return modelMapper.map(
			roomsRepository.insert(modelMapper.map(request.toBuilder()
				.id(UUID.randomUUID().toString())
				.build(), RoomEntity.class)), 
			AddRoomRequestDTO.class
		);
	}
	
	public void delete(String id) {
		log.info("deleteRoom - input [{}]", id);
		roomsRepository.deleteById(id);
	}
	
}
