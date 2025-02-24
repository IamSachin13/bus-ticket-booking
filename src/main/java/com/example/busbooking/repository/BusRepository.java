// src/main/java/com/example/busbooking/repository/BusRepository.java
package com.example.busbooking.repository;

import com.example.busbooking.model.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BusRepository extends MongoRepository<Bus, String> {}
