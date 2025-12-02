package com.busbooking.controller;

import com.busbooking.model.Route;
import com.busbooking.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RouteController {
    
    private final RouteService routeService;
    
    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        return ResponseEntity.ok(routeService.getAllRoutes());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable String id) {
        return ResponseEntity.ok(routeService.getRouteById(id));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Route>> searchRoutes(
            @RequestParam String fromCity,
            @RequestParam String toCity) {
        return ResponseEntity.ok(routeService.searchRoutes(fromCity, toCity));
    }
    
    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<Route>> getRoutesByBus(@PathVariable String busId) {
        return ResponseEntity.ok(routeService.getRoutesByBus(busId));
    }
    
    @PostMapping
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        return ResponseEntity.status(HttpStatus.CREATED).body(routeService.createRoute(route));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable String id, @RequestBody Route route) {
        return ResponseEntity.ok(routeService.updateRoute(id, route));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable String id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
}