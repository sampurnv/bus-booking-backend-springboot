package com.busbooking.controller;

import com.busbooking.model.Bus;
import com.busbooking.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BusController {
    
    private final BusService busService;
    
    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        return ResponseEntity.ok(busService.getAllBuses());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable String id) {
        return ResponseEntity.ok(busService.getBusById(id));
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<Bus>> getActiveBuses() {
        return ResponseEntity.ok(busService.getActiveBuses());
    }
    
    @GetMapping("/type/{busType}")
    public ResponseEntity<List<Bus>> getBusesByType(@PathVariable String busType) {
        return ResponseEntity.ok(busService.getBusesByType(busType));
    }
    
    @PostMapping
    public ResponseEntity<Bus> createBus(@RequestBody Bus bus) {
        return ResponseEntity.status(HttpStatus.CREATED).body(busService.createBus(bus));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Bus> updateBus(@PathVariable String id, @RequestBody Bus bus) {
        return ResponseEntity.ok(busService.updateBus(id, bus));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable String id) {
        busService.deleteBus(id);
        return ResponseEntity.noContent().build();
    }
}