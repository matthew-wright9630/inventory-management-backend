package com.skillstorm.inventory_management_backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.inventory_management_backend.models.Warehouse;
import com.skillstorm.inventory_management_backend.services.WarehouseService;
import com.skillstorm.inventory_management_backend.validators.WarehouseValidator;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;
    private WarehouseValidator validator;

    public WarehouseController(WarehouseService warehouseService, WarehouseValidator validator) {
        this.warehouseService = warehouseService;
        this.validator = validator;
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
    public String getMethodName(@RequestParam(required = false) int capacityPercent) {
        return new String();
    }

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        try {
            validator.validateWarehouse(warehouse);
            Warehouse returnedWarehouse = warehouseService.createWarehouse(warehouse);
            return new ResponseEntity<>(returnedWarehouse, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

}
