package com.hotels.lastcancunhotel.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hotels.lastcancunhotel.entities.BookEntity;

@Repository
public interface BookRepository extends MongoRepository<BookEntity, String>{

}
	