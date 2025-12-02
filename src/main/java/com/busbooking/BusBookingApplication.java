package com.busbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class BusBookingApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(BusBookingApplication.class, args);
        System.out.println("🚌 Bus Booking Application Started Successfully!");
        System.out.println("📍 Server running on: http://localhost:8080");
        System.out.println("📚 API Documentation: http://localhost:8080/api");
    }
}