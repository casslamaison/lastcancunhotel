package com.hotels.lastcancunhotel.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotels.lastcancunhotel.dtos.RoomDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class RoomsAvailabilityServiceTest {

	@Test
	public void teste() throws ParseException {
		RoomDTO roomDTO = new RoomDTO();
		Assertions.assertNotNull(roomDTO);
		String checkInBanco = "05-06-2022";
		String checkOutBanco = "09-06-2022";
		
		String checkInp = "10-06-2022";
		String checkOutp = "10-06-2022";
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
		Date StartDate1 = formatter.parse(checkInp);
		Date EndDate1 = formatter.parse(checkOutp);
		
		Date StartDate2 = formatter.parse(checkInBanco);
		Date EndDate2 = formatter.parse(checkOutBanco);
		
		
		if((StartDate1.compareTo(EndDate2) <= 0) && (StartDate2.compareTo(EndDate1) <= 0)) {
			log.info("On the range");
		}
		
		
	}
}
