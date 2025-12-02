package com.busbooking.repository;

import com.busbooking.model.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends MongoRepository<Bus, String> {
    
    Optional<Bus> findByBusNumber(String busNumber);
    
    List<Bus> findByBusType(String busType);
    
    List<Bus> findByOperatorName(String operatorName);
    
    List<Bus> findByIsActive(boolean isActive);
}