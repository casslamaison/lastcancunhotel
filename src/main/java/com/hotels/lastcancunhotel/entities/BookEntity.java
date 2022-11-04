package com.hotels.lastcancunhotel.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder=true)
@Document("booking")
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {

	@Id
	private String id;
	private Date checkin;
	private Date checkout;
	private RoomEntity room;
	
}
