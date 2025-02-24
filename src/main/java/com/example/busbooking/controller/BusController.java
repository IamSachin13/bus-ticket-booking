// src/main/java/com/example/busbooking/controller/BusController.java
package com.example.busbooking.controller;

import com.example.busbooking.model.Bus;
import com.example.busbooking.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/buses")
public class BusController {

    @Autowired
    private BusRepository busRepository;

    // REST endpoint to get all buses as JSON
    @GetMapping
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    // Thymeleaf view to display bus routes
    @GetMapping("/view")
    public String viewBuses(Model model) {
        List<Bus> buses = busRepository.findAll();
        model.addAttribute("buses", buses);
        return "buses"; // Corresponds to src/main/resources/templates/buses.html
    }

    // New endpoint to input (create) a Bus entity
    @PostMapping("/add-bus")
    public Bus createBus(@RequestBody Bus bus) {
        return busRepository.save(bus);
    }
    // Endpoint to delete a Bus by its ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBus(@PathVariable String id) {
        Optional<Bus> busOptional = busRepository.findById(id);
        if (!busOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Bus not found with id: " + id);
        }
        busRepository.deleteById(id);
        return ResponseEntity.ok("Bus deleted successfully");
    }

}
