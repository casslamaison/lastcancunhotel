package com.hotels.lastcancunhotel.services;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hotels.lastcancunhotel.dtos.BookingRequestDTO;
import com.hotels.lastcancunhotel.dtos.BookingResponseDTO;
import com.hotels.lastcancunhotel.entities.BookEntity;
import com.hotels.lastcancunhotel.entities.RoomEntity;
import com.hotels.lastcancunhotel.repositories.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookingService {

	private BookRepository bookRepository;
	private RoomsService roomsService;
	
	public BookingService (BookRepository bookRepository, RoomsService roomsService) {
		this.bookRepository = bookRepository;
		this.roomsService = roomsService;
	}
	
	public List<BookEntity> getBookedRoomsWithinRange(Date checkIn, Date checkOut) {
		Date checkinn = Date.from(checkIn.toInstant().minus(Duration.ofDays(1)));
		return bookRepository.findBookingsByCheckinAndCheckout(checkinn, checkOut);
	}
	
	public void cancelBooking(String id) {
		log.info("Canceling booking '{}'", id);
		bookRepository.deleteById(id);
	}
	
	public List<BookingResponseDTO> listBookings(){
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
	
	public BookEntity bookRoom(BookingRequestDTO request) {
		checkIfBookingExists(request);
		
		RoomEntity roomEntity = roomsService.findRoomEntityById(request.getRoomId());
		
		return bookRepository.insert(BookEntity.builder()
				.id(UUID.randomUUID().toString())
				.checkin(request.getCheckIn())
				.checkout(request.getCheckOut())
				.room(roomEntity)
				.build());
	}
	
	public BookEntity modifyBooking(BookingRequestDTO request) {
		
		this.getBookedRoomsWithinRange(request.getCheckIn(), request.getCheckOut()).stream()
				.filter(entity -> !entity.getId().equals(request.getId()))
				.findAny().map(handleExistingBooking());
		
		BookEntity modifyBooking = this.bookRepository.findById(request.getId()).orElseThrow()
				.toBuilder()
				.checkin(request.getCheckIn())
				.checkout(request.getCheckOut())
				.build();
		
		return bookRepository.save(modifyBooking);
	}

	private void checkIfBookingExists(BookingRequestDTO request) {
		this.getBookedRoomsWithinRange(request.getCheckIn(), request.getCheckOut()).stream()
				.filter(entity -> entity.getRoom().getId().equals(request.getRoomId()))
				.findAny().map(handleExistingBooking());
	}
	
	private Function<BookEntity, Object> handleExistingBooking() {
		return item -> {
			throw new RuntimeException("Booking unavailable for this period."); 
		};
	}
	
}
