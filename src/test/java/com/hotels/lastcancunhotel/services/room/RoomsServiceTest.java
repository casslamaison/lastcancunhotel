package com.hotels.lastcancunhotel.services.room;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotels.lastcancunhotel.entities.RoomEntity;
import com.hotels.lastcancunhotel.repositories.RoomsRepository;

@ExtendWith(MockitoExtension.class)
public class RoomsServiceTest {

	@InjectMocks
	RoomsService roomsService;
	
	@Mock
	RoomsRepository roomsRepository;
	
	@Test
	public void shouldFindRoomById() {
		String id = "8325238c-0c61-4076-95ad-3e9728b795a2";
		when(roomsRepository.findById(id))
			.thenReturn(Optional.of(RoomEntity.builder().id(id).build()));
		
		RoomEntity room = roomsService.findById(id);
		
		assertThat(room.getId()).isNotEmpty();
	}
	
	@Test
	public void shouldThrowExceptionTryingToFindRoomById() {
		when(roomsRepository.findById(Mockito.any()))
			.thenThrow(RuntimeException.class);
	
		Exception exception = assertThrows(RuntimeException.class, ()-> {
			roomsService.findById("");
	    });

	    assertThat(exception.getClass()).isEqualTo(RuntimeException.class);
	}
	
}
