package com.hotels.lastcancunhotel.services.booking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotels.lastcancunhotel.dtos.BookingRequestDTO;
import com.hotels.lastcancunhotel.dtos.BookingResponseDTO;
import com.hotels.lastcancunhotel.entities.BookEntity;
import com.hotels.lastcancunhotel.entities.RoomEntity;
import com.hotels.lastcancunhotel.repositories.BookRepository;
import com.hotels.lastcancunhotel.services.room.Room;
import com.mongodb.client.MongoClient;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

	@InjectMocks
	BookingService bookingService;
	
	@Mock 
	MongoClient mongoDbFactory;   
	
	@Mock
	private BookRepository bookRepository;
	
	@Mock
	private Room roomService;
	
	private static final String BOOK_ERROR_MESSAGE = "cannot be reserved";
	
	private Date createDateFrom(int plusDays) {
		return Date.from(LocalDate.now().plusDays(plusDays).atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	private BookingRequestDTO defaultBookingRequestFromPeriod(Date checkIn, Date checkOut) {
		return BookingRequestDTO.builder()
				.checkIn(checkIn)
				.checkOut(checkOut)
				.build();
	}
	
	@Test
	public void shouldCancelBooking() {
		String bookingId = "EMPTY";
		bookingService.cancel(bookingId);
		verify(bookRepository, times(1)).deleteById(bookingId);
	}
	
	@Test
	public void shouldListAllBookings() {
		when(bookRepository.findAll())
			.thenReturn(Arrays.asList(BookEntity.builder()
					.room(RoomEntity.builder().build())
					.build()));
		
		List<BookingResponseDTO> bookings = bookingService.list();
		
		assertThat(bookings).isNotEmpty();
	}
	
	@Test
	public void shouldBookRoom() {
		Date checkIn = createDateFrom(2);
		Date checkOut = createDateFrom(4);
		
		when(bookRepository.insert(Mockito.any(BookEntity.class)))
			.thenReturn(BookEntity.builder().build());
		
		BookingRequestDTO request = BookingRequestDTO.builder()
				.checkIn(checkIn)
				.checkOut(checkOut)
				.build();
		
		BookEntity s = bookingService.book(request);
		
		assertThat(s).isNotNull();
	}
	
	@Test
	public void givenStayLongerThan3DaysShouldNotBookRoom() {
		Date checkIn = createDateFrom(5);
		Date checkOut = createDateFrom(15);
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			bookingService.book(defaultBookingRequestFromPeriod(checkIn, checkOut));
	    });
		
		assertTrue(exception.getMessage().contains(BOOK_ERROR_MESSAGE));
	}
	
	@Test
	public void givenCheckInDateMoreThan30DaysInAdvanceShouldNotBookRoom() {
		Date checkIn = createDateFrom(31);
		Date checkOut = createDateFrom(32);
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			bookingService.book(defaultBookingRequestFromPeriod(checkIn, checkOut));
	    });
		
		assertTrue(exception.getMessage().contains(BOOK_ERROR_MESSAGE));
	}
	
	@Test
	public void givenCheckInDateLessThanCheckOutDateShouldNotBookRoom() {
		Date checkIn = createDateFrom(2);
		Date checkOut = createDateFrom(1);
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			bookingService.book(defaultBookingRequestFromPeriod(checkIn, checkOut));
	    });
		
		assertTrue(exception.getMessage().contains(BOOK_ERROR_MESSAGE));
	}
	
	@Test
	public void shouldModifyBooking() {
		Date checkIn = createDateFrom(2);
		Date checkOut = createDateFrom(4);
		
		when(bookRepository.findById(Mockito.any()))
			.thenReturn(Optional.of(BookEntity.builder().build()));
		
		when(bookRepository.save(Mockito.any()))
			.thenReturn(BookEntity.builder().build());
		
		BookEntity modifiedBooking = bookingService.modify(defaultBookingRequestFromPeriod(checkIn, checkOut));
		
		assertThat(modifiedBooking).isNotNull();
	}
	
}
