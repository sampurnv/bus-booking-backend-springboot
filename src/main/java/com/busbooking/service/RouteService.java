package com.busbooking.service;

import com.busbooking.model.Route;
import com.busbooking.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RouteService {
    
    private final RouteRepository routeRepository;
    
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }
    
    public Route getRouteById(String id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + id));
    }
    
    public Route createRoute(Route route) {
        log.info("Creating new route from {} to {}", route.getFromCity(), route.getToCity());
        return routeRepository.save(route);
    }
    
    public Route updateRoute(String id, Route routeDetails) {
        Route route = getRouteById(id);
        route.setBusId(routeDetails.getBusId());
        route.setFromCity(routeDetails.getFromCity());
        route.setToCity(routeDetails.getToCity());
        route.setDepartureTime(routeDetails.getDepartureTime());
        route.setArrivalTime(routeDetails.getArrivalTime());
        route.setDuration(routeDetails.getDuration());
        route.setDistance(routeDetails.getDistance());
        route.setBaseFare(routeDetails.getBaseFare());
        route.setBoardingPoints(routeDetails.getBoardingPoints());
        route.setDroppingPoints(routeDetails.getDroppingPoints());
        route.setDaysOfOperation(routeDetails.getDaysOfOperation());
        route.setActive(routeDetails.isActive());
        return routeRepository.save(route);
    }
    
    public void deleteRoute(String id) {
        Route route = getRouteById(id);
        routeRepository.delete(route);
        log.info("Deleted route: {}", id);
    }
    
    public List<Route> searchRoutes(String fromCity, String toCity) {
        return routeRepository.findByFromCityAndToCityAndIsActive(fromCity, toCity, true);
    }
    
    public List<Route> getRoutesByBus(String busId) {
        return routeRepository.findByBusId(busId);
    }
}