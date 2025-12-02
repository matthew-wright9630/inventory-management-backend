package com.skillstorm.inventory_management_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management_backend.models.Location;
import com.skillstorm.inventory_management_backend.repositories.LocationRepository;

@Service
public class LocationService {
    
    private final LocationRepository locationRepository;

    LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    public Location findLocationById(int id) {
        Optional<Location> location = locationRepository.findById(id);

        if (location.isPresent()) {
            return location.get();
        }
        return null;
    }

    public Location saveLocation(Location location) {
        locationRepository.save(location);
        return location;
    }

    public Location deleteLocation(Location location) {
        locationRepository.deleteLocation(location.getId(), false);
        return location;
    }
}
