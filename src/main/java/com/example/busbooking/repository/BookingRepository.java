// src/main/java/com/example/busbooking/repository/BookingRepository.java
package com.example.busbooking.repository;

import com.example.busbooking.model.Booking;
import com.example.busbooking.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findByUser(User user);
}
