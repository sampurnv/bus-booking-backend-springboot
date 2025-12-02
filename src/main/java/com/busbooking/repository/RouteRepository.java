package com.busbooking.repository;

import com.busbooking.model.Route;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends MongoRepository<Route, String> {
    
    List<Route> findByFromCityAndToCity(String fromCity, String toCity);
    
    List<Route> findByBusId(String busId);
    
    List<Route> findByFromCityAndToCityAndIsActive(String fromCity, String toCity, boolean isActive);
}