package com.hotels.lastcancunhotel.services.room;

import java.util.List;

import com.hotels.lastcancunhotel.dtos.RoomDTO;
import com.hotels.lastcancunhotel.dtos.RoomRequestDTO;

public interface RoomAvailability {
	
	public List<RoomDTO> listAvailable(RoomRequestDTO request);
	
}
