package com.busbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "buses")
public class Bus {
    
    @Id
    private String id;
    
    private String busNumber;
    private String busName;
    private String busType; // AC, Non-AC, Sleeper, Semi-Sleeper
    private String operatorName;
    private int totalSeats;
    private List<String> amenities; // WiFi, Charging Point, Water Bottle, etc.
    private String imageUrl;
    private boolean isActive;
    
    // Seat Layout
    private int rows;
    private int seatsPerRow;
    private SeatLayout seatLayout;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SeatLayout {
        private String type; // 2x2, 2x3, etc.
        private List<String> unavailableSeats; // Seats that don't exist (like driver area)
    }
}