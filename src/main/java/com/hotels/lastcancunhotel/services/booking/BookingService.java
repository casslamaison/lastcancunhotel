package com.hotels.lastcancunhotel.services.booking;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hotels.lastcancunhotel.dtos.BookingRequestDTO;
import com.hotels.lastcancunhotel.dtos.BookingResponseDTO;
import com.hotels.lastcancunhotel.entities.BookEntity;
import com.hotels.lastcancunhotel.entities.RoomEntity;
import com.hotels.lastcancunhotel.repositories.BookRepository;
import com.hotels.lastcancunhotel.services.room.Room;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
class BookingService implements Booking {

	private BookRepository bookRepository;
	private Room roomsService;
	
	public BookingService (BookRepository bookRepository, Room roomsService) {
		this.bookRepository = bookRepository;
		this.roomsService = roomsService;
	}
	
	public List<BookEntity> listWithinRange(Date checkIn, Date checkOut) {
		log.info("getBookedRoomsWithinRange - input [{}, {}]", checkIn, checkOut);
		Date checkinn = Date.from(checkIn.toInstant().minus(Duration.ofDays(1)));
		return bookRepository.findBookingsByCheckinAndCheckout(checkinn, checkOut);
	}
	
	public void cancel(String id) {
		log.info("cancelBooking - input [{}]", id);
		bookRepository.deleteById(id);
	}
	
	public List<BookingResponseDTO> list(){
		log.info("listBookings");
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
	
	public BookEntity book(BookingRequestDTO request) {
		log.info("bookRoom - input [{}]", request);
		checkIfBookingExists(request);
		validateBusinessRules(request.getCheckIn(), request.getCheckOut());
		
		RoomEntity roomEntity = roomsService.findById(request.getRoomId());
		
		return bookRepository.insert(BookEntity.builder()
			.id(UUID.randomUUID().toString())
			.checkin(request.getCheckIn())
			.checkout(request.getCheckOut())
			.room(roomEntity)
			.build());
	}
	
	public BookEntity modify(BookingRequestDTO request) {
		log.info("modifyBooking - input [{}]", request);
		validateBusinessRules(request.getCheckIn(), request.getCheckOut());
		
		this.listWithinRange(request.getCheckIn(), request.getCheckOut()).stream()
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
		this.listWithinRange(request.getCheckIn(), request.getCheckOut()).stream()
			.filter(entity -> entity.getRoom().getId().equals(request.getRoomId()))
			.findAny().map(handleExistingBooking());
	}
	
	private void validateBusinessRules(Date checkIn, Date checkout) {
		BiPredicate<Date, Date> dateShouldBeBiggerThan =  
			(firstDate, secondDate) -> firstDate.compareTo(secondDate) < 0;
				
		BiPredicate<Date, Date> differenceShouldNotBeBiggerThen =  
			(firstDate, secondDate) -> Duration.between(firstDate.toInstant(), secondDate.toInstant()).toDays() < 3;
		
		BiPredicate<Date, Date> dateInAdvance = (firstDate, secondDate) -> Duration.between(new Date().toInstant()	, firstDate.toInstant()).toDays() < 30;
		
		if(!Arrays.asList(dateShouldBeBiggerThan, differenceShouldNotBeBiggerThen, dateInAdvance).stream()
			.allMatch(biPredicate -> biPredicate.test(checkIn, checkout))) {
				throw new RuntimeException("The stay period should not be longer than 3 days and it cannot be reserved for more than 30 days in advance.");
		}
	}
	
	private Function<BookEntity, Object> handleExistingBooking() {
		return item -> {
			throw new RuntimeException("Room unavailable for this period."); 
		};
	}

}
