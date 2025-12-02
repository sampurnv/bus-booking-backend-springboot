package com.busbooking.service;

import com.busbooking.model.Bus;
import com.busbooking.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BusService {
    
    private final BusRepository busRepository;
    
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }
    
    public Bus getBusById(String id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found with id: " + id));
    }
    
    public Bus createBus(Bus bus) {
        log.info("Creating new bus: {}", bus.getBusNumber());
        return busRepository.save(bus);
    }
    
    public Bus updateBus(String id, Bus busDetails) {
        Bus bus = getBusById(id);
        bus.setBusNumber(busDetails.getBusNumber());
        bus.setBusName(busDetails.getBusName());
        bus.setBusType(busDetails.getBusType());
        bus.setOperatorName(busDetails.getOperatorName());
        bus.setTotalSeats(busDetails.getTotalSeats());
        bus.setAmenities(busDetails.getAmenities());
        bus.setImageUrl(busDetails.getImageUrl());
        bus.setRows(busDetails.getRows());
        bus.setSeatsPerRow(busDetails.getSeatsPerRow());
        bus.setSeatLayout(busDetails.getSeatLayout());
        bus.setActive(busDetails.isActive());
        return busRepository.save(bus);
    }
    
    public void deleteBus(String id) {
        Bus bus = getBusById(id);
        busRepository.delete(bus);
        log.info("Deleted bus: {}", id);
    }
    
    public List<Bus> getBusesByType(String busType) {
        return busRepository.findByBusType(busType);
    }
    
    public List<Bus> getActiveBuses() {
        return busRepository.findByIsActive(true);
    }
}