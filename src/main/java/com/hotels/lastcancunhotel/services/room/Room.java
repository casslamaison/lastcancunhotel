package com.hotels.lastcancunhotel.services.room;

import java.util.List;

import com.hotels.lastcancunhotel.dtos.AddRoomRequestDTO;
import com.hotels.lastcancunhotel.dtos.RoomDTO;
import com.hotels.lastcancunhotel.entities.RoomEntity;

public abstract interface Room {
	public RoomEntity findById(String id);
	
	public List<RoomDTO> listAll();
	
	public AddRoomRequestDTO add(AddRoomRequestDTO request);
	
	public void delete(String id);
	
}
