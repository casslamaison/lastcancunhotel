package com.hotels.lastcancunhotel.dtos;

import java.util.List;

import com.hotels.lastcancunhotel.entities.AmenityEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDTO {
	private int id;
	private String token;
	String name;
	String description;
	List<AmenityEntity> amenities;
	String location;
}
