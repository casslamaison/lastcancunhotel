package com.hotels.lastcancunhotel.dtos;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HotelDTO {
	private int id;
	private List<RoomDTO> rooms;
}
