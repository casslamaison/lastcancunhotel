package com.hotels.lastcancunhotel.dtos;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddRoomRequestDTO {
	String id;
	String name;
	String description;
	List<String> amenities;
	String location;
}
