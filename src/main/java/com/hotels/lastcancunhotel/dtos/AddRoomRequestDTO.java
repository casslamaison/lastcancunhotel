package com.hotels.lastcancunhotel.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AddRoomRequestDTO {
	String id;
	String name;
	String description;
	List<String> amenities;
	String location;
}
