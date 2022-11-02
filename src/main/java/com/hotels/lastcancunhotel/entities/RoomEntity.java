package com.hotels.lastcancunhotel.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Document("rooms")
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntity {

	@Id
	private String id;
	String name;
	String description;
	List<AmenityEntity> amenities;
	String location;
	
}
