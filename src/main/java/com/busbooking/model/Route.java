package com.busbooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "routes")
public class Route {
    
    @Id
    private String id;
    
    private String busId;
    private String fromCity;
    private String toCity;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String duration; // e.g., "6h 30m"
    private double distance; // in kilometers
    private double baseFare;
    private List<String> boardingPoints;
    private List<String> droppingPoints;
    private List<String> daysOfOperation; // Monday, Tuesday, etc.
    private boolean isActive;
}