package com.hotels.lastcancunhotel.services;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hotels.lastcancunhotel.dtos.HotelDTO;
import com.hotels.lastcancunhotel.dtos.HotelRequestDTO;

@Service
public class HotelsService {

	public List<HotelDTO> getHotels(){
		return Collections.emptyList();
	}
	
	public List<HotelDTO> getAvail(HotelRequestDTO request){
		long start = System.currentTimeMillis();
		//TODO: Not implemented yet
		return Collections.emptyList();
	}
	
}
