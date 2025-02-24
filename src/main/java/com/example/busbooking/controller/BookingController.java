// src/main/java/com/example/busbooking/controller/BookingController.java
package com.example.busbooking.controller;

import com.example.busbooking.model.Booking;
import com.example.busbooking.model.Bus;
import com.example.busbooking.model.Passenger;
import com.example.busbooking.model.User;
import com.example.busbooking.repository.BookingRepository;
import com.example.busbooking.repository.BusRepository;
import com.example.busbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new booking
    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest bookingRequest, Authentication authentication) {
        String email = authentication.getName();
        User user = (User) userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        Bus bus = busRepository.findById(bookingRequest.getBusId()).orElse(null);
        if (bus == null) {
            return ResponseEntity.badRequest().body("Bus not found");
        }
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBus(bus);
        booking.setBookingTime(LocalDateTime.now());
        booking.setPassengers(bookingRequest.getPassengers());
        bookingRepository.save(booking);
        return ResponseEntity.ok("Booking successful");
    }

    // DTO for booking requests
    public static class BookingRequest {
        private String busId;
        private List<Passenger> passengers;
        public String getBusId() { return busId; }
        public void setBusId(String busId) { this.busId = busId; }
        public List<Passenger> getPassengers() { return passengers; }
        public void setPassengers(List<Passenger> passengers) { this.passengers = passengers; }
    }
}
