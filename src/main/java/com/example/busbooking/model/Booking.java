// src/main/java/com/example/busbooking/model/Booking.java
package com.example.busbooking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "bookings")
public class Booking {
    @Id
    private String id;

    // Reference to the user who made the booking
    @DBRef
    private User user;

    // List of passengers in the booking
    private List<Passenger> passengers;

    // Reference to the bus booked
    @DBRef
    private Bus bus;

    private LocalDateTime bookingTime;

    // Getters and setters
    public String getId() { return id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public List<Passenger> getPassengers() { return passengers; }
    public void setPassengers(List<Passenger> passengers) { this.passengers = passengers; }
    public Bus getBus() { return bus; }
    public void setBus(Bus bus) { this.bus = bus; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }
}
