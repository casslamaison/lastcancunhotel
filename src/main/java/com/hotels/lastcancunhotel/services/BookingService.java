package com.hotels.lastcancunhotel.services;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hotels.lastcancunhotel.dtos.BookingRequestDTO;
import com.hotels.lastcancunhotel.dtos.BookingResponseDTO;
import com.hotels.lastcancunhotel.entities.BookEntity;
import com.hotels.lastcancunhotel.entities.RoomEntity;
import com.hotels.lastcancunhotel.repositories.BookRepository;

@Service
public class BookingService {

	private BookRepository bookRepository;
	private RoomsService roomsService;
	
	public BookingService (BookRepository bookRepository, RoomsService roomsService) {
		this.bookRepository = bookRepository;
		this.roomsService = roomsService;
	}
	
	public BookEntity bookRoom(BookingRequestDTO request) {
		
		RoomEntity roomEntity = roomsService.findRoomEntityById(request.getRoomId());
		
//		List<BookEntity> s = bookRepository.findByCheckinBetweenAndCheckoutBetween(request.getCheckIn(), request.getCheckOut());
		
		return bookRepository.insert(BookEntity.builder()
				.id(request.getId())
				.checkin(request.getCheckIn())
				.checkout(request.getCheckOut())
				.room(roomEntity)
				.build());
	}
	
	public List<BookEntity> getBookedRooms(Date checkIn, Date checkOut) {
		Date checkinn = Date.from(checkIn.toInstant().minus(Duration.ofDays(1)));
		return bookRepository.checkAvailableBookings(checkinn, checkOut);
	}
	
	public void removeBooking(String id) {
		bookRepository.deleteById(id);
	}
	
	public List<BookingResponseDTO> getAllBookings(){
		return bookRepository.findAll().stream()
				.map(entity -> BookingResponseDTO.builder()
						.id(entity.getId())
						.checkIn(entity.getCheckin())
						.checkOut(entity.getCheckout())
						.roomId(entity.getRoom().getId())
						.roomName(entity.getRoom().getName())
						.build())
				.collect(Collectors.toList());
	}
}
