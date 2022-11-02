package com.hotels.lastcancunhotel.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hotels.lastcancunhotel.entities.RoomEntity;

@Repository
public interface RoomsRepository extends MongoRepository<RoomEntity, String>{

}
