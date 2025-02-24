// src/main/java/com/example/busbooking/repository/PassengerRepository.java
package com.example.busbooking.repository;

import com.example.busbooking.model.Passenger;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PassengerRepository extends MongoRepository<Passenger, String> {}
