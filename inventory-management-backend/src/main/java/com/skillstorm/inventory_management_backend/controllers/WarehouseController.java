package com.skillstorm.inventory_management_backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.inventory_management_backend.models.Warehouse;
import com.skillstorm.inventory_management_backend.repositories.LocationRepository;
import com.skillstorm.inventory_management_backend.services.WarehouseService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/warehouses")
@CrossOrigin({ "http://127.0.0.1:5500/" })
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService, LocationRepository locationRepository) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        try {
            List<Warehouse> warehouses = warehouseService.findAllWarehouses();
            return new ResponseEntity<>(warehouses, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    /**
     * This is going to send back a list of warehouses where the capacity is
     * capicityPercent full. (such as 90 full).
     * 
     * @param capacityPercent
     * @return
     */
    @GetMapping("/capacity")
    public ResponseEntity<List<Warehouse>> getWarehouseByCapacityLeft(
            @RequestParam(defaultValue = "90") int capacityPercent) {
        try {
            List<Warehouse> warehouses = warehouseService.findWarehousesByCapacityLeft(capacityPercent);
            return new ResponseEntity<>(warehouses, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse, @RequestParam int locationId) {
        try {
            Warehouse returnedWarehouse = warehouseService.createWarehouse(warehouse, locationId);
            return new ResponseEntity<>(returnedWarehouse, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PutMapping
    public ResponseEntity<Warehouse> updateWarehouse(@RequestBody Warehouse warehouse,
            @RequestParam(required = false) Integer locationId) {
        try {
            Warehouse updatedWarehouse;
            if (locationId == null) {
                updatedWarehouse = warehouseService.saveWarehouse(warehouse);
            } else {
                updatedWarehouse = warehouseService.saveWarehouse(warehouse, locationId);
            }
            return new ResponseEntity<>(updatedWarehouse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable int id) {
        try {
            warehouseService.deleteWarehouse(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

}
