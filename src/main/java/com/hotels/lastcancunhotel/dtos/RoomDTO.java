package com.hotels.lastcancunhotel.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomDTO {
	private int id;
	private String propertyId;
	private String token;
}
