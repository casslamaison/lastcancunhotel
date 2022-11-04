package com.hotels.lastcancunhotel.configuration;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.hotels.lastcancunhotel.entities.RoomEntity;
import com.hotels.lastcancunhotel.repositories.RoomsRepository;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "com.hotels.lastcancunhotel.repositories")
public class MongoConfiguration {

	@Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/lastcancunhotel");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
          .applyConnectionString(connectionString)
          .build();
        
        return MongoClients.create(mongoClientSettings);
    }
    
	@Bean
    CommandLineRunner commandLineRunner(RoomsRepository roomsRepository) {
        return strings -> {
        	roomsRepository.save(RoomEntity.builder()
        			.id("1da48796-d3a6-432a-a3df-ee43c31cb37d")
        			.name("Quarto duplo")
        			.description("Quarto duplo master com chuveiro")
        			.amenities(Arrays.asList("Ar condicionado", "Fogão", "Gás"))
        			.location("Rua chimarrão 43")
        			.build());
        };
    }
	
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
}
