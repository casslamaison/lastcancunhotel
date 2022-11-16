package com.hotels.lastcancunhotel.services.room;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.hotels.lastcancunhotel.dtos.RoomDTO;
import com.hotels.lastcancunhotel.dtos.RoomRequestDTO;
import com.hotels.lastcancunhotel.entities.RoomEntity;
import com.hotels.lastcancunhotel.repositories.RoomsRepository;
import com.hotels.lastcancunhotel.services.booking.Booking;
import com.mongodb.client.MongoClient;

@ExtendWith(MockitoExtension.class)
public class RoomsAvailabilityServiceTest {

	@Mock 
	MongoClient mongoDbFactory;    
	
	@Mock
	RoomsRepository roomsRepository;
	
	@Mock
	Booking bookingService;
	
	@Mock
    private ModelMapper mapper;
	
	@InjectMocks
	RoomsAvailabilityService roomsAvailabilityService;
	
	private static final String ERROR_MESSAGE = "An error has ocurred";
	@Test
	public void shouldNotReturnAnyAvailableRooms() throws ParseException {
		RoomRequestDTO request = RoomRequestDTO.builder().build();
		
		when(roomsRepository.findAll())
				.thenReturn(Collections.emptyList());
		
		List<RoomDTO> availableRooms = roomsAvailabilityService.listAvailable(request);
		
		assertThat(availableRooms).isEmpty();
	}
	
	@Test
	public void shouldReturnAvailableRooms() throws ParseException {
		RoomRequestDTO request = RoomRequestDTO.builder().build();
		
		when(roomsRepository.findAll())
				.thenReturn(Arrays.asList(RoomEntity.builder().build()));
		
		when(mapper.map(Mockito.any(), Mockito.any()))
				.thenReturn(RoomDTO.builder().build());
		
		List<RoomDTO> availableRooms = roomsAvailabilityService.listAvailable(request);
		
		assertThat(availableRooms).isNotEmpty();
	}
	
	@Test
	public void shouldThrowExceptionWhenTryingToFetchAvailableRooms() throws ParseException {
		RoomRequestDTO request = RoomRequestDTO.builder().build();
		
		when(bookingService.listWithinRange(Mockito.any(), Mockito.any()))
				.thenThrow(new RuntimeException(ERROR_MESSAGE));
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			roomsAvailabilityService.listAvailable(request);
	    });

	    assertTrue(exception.getMessage().contains(ERROR_MESSAGE));
	}
	
}
