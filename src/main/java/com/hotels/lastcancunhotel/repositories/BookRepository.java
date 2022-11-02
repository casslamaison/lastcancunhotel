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
	public List<BookEntity> checkAvailableBookings(Date checkin, Date checkout);
	
}
//(StartParam1 <= EndDB2) and (StartDB2 <= EndParam1)
//if((StartParam1.compareTo(EndDB2) <= 0) && (StartDB2.compareTo(EndParam1) <= 0)) {
//	log.info("TÁ NO RANGE MALUCO");
//}
//db.inventory.find( { status: "A", qty: { $lt: 30 } } )

//MongoDB Shell
//
//The operation corresponds to the following SQL statement:
//
//SELECT * FROM inventory WHERE status = "A" AND qty < 30
