package com.skillstorm.inventory_management_backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.inventory_management_backend.models.Location;
import com.skillstorm.inventory_management_backend.services.LocationService;
import com.skillstorm.inventory_management_backend.validators.LocationValidator;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<Location>> findAllLocations() {
        try {
            List<Location> locations = locationService.findAllLocations();
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        try {
            return new ResponseEntity<>(locationService.saveLocation(location), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PutMapping
    public ResponseEntity<Location> updateLocation(@RequestBody Location location) {
        try {
            LocationValidator.validateLocation(location);
            Location newLocation = locationService.saveLocation(location);
            return new ResponseEntity<Location>(newLocation, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable int id) {
        try {
            locationService.deleteLocation(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
}
