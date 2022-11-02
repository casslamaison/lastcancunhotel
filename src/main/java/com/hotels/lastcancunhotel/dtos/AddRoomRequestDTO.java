package com.hotels.lastcancunhotel.dtos;

import java.util.List;

import lombok.Data;

@Data
public class AddRoomRequestDTO {
	String id;
	String name;
	String description;
	List<String> amenities;
	String location;
}
