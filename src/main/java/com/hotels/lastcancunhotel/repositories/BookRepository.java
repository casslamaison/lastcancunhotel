package com.hotels.lastcancunhotel.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotels.lastcancunhotel.entities.BookEntity;

@Repository
public interface BookRepository extends MongoRepository<BookEntity, String>{

	@Query("{checkout : {$gt : ?0}, checkin: {$lt : ?1}}")
	public List<BookEntity> findBookingsByCheckinAndCheckout(Date checkIn, Date checkOut);
	
}
