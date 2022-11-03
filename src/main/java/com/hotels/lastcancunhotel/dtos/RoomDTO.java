package com.hotels.lastcancunhotel.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder=true)
public class RoomDTO {
	private String id;
	String name;
	String description;
	List<String> amenities;
	String location;
}
