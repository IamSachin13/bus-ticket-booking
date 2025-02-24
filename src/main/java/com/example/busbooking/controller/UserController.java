// src/main/java/com/example/busbooking/controller/UserController.java
package com.example.busbooking.controller;

import com.example.busbooking.model.Booking;
import com.example.busbooking.model.User;
import com.example.busbooking.repository.BookingRepository;
import com.example.busbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // Retrieve user profile
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(Authentication authentication) {
        String email = authentication.getName();
        User user = (User) userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok(user);
    }

    // Update user profile
    @PutMapping("/profile")
    public ResponseEntity<?> updateUserProfile(@RequestBody User updatedUser, Authentication authentication) {
        String email = authentication.getName();
        User user = (User) userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        user.setName(updatedUser.getName());
        // Optionally update other fields (email should generally remain unchanged)
        userRepository.save(user);
        return ResponseEntity.ok("Profile updated successfully");
    }

    // Get booking history for the user
    @GetMapping("/bookings")
    public ResponseEntity<?> getBookingHistory(Authentication authentication) {
        String email = authentication.getName();
        User user = (User) userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        List<Booking> bookings = bookingRepository.findByUser(user);
        return ResponseEntity.ok(bookings);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().body("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
