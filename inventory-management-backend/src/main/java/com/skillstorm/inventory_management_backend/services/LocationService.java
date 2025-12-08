package com.skillstorm.inventory_management_backend.services;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management_backend.models.Location;
import com.skillstorm.inventory_management_backend.repositories.LocationRepository;
import com.skillstorm.inventory_management_backend.validators.LocationValidator;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    public Location findLocationById(int id) throws IllegalArgumentException {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent()) {
            return location.get();
        }
        throw new IllegalArgumentException("Location does not exist. Please try with another location.");
    }

    public Location saveLocation(Location location) throws IllegalArgumentException {
        if (LocationValidator.validateLocation(location)) {
            locationRepository.save(location);
            return location;
        }
        throw new IllegalArgumentException("Values were not input as expected. input: " + location);
    }

    public Location deleteLocation(int locationId) {
        Location foundLocation = findLocationById(locationId);
        if (foundLocation.getId() > 0) {
            locationRepository.deleteLocation(foundLocation.getId(), false);
            return foundLocation;
        }
        throw new IllegalArgumentException("Location does not exist. Please try with another location.");
    }
}
