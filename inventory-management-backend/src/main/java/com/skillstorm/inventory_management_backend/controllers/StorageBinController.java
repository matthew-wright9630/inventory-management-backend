package com.skillstorm.inventory_management_backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.inventory_management_backend.models.StorageBin;
import com.skillstorm.inventory_management_backend.models.Warehouse;
import com.skillstorm.inventory_management_backend.services.StorageBinService;
import com.skillstorm.inventory_management_backend.services.WarehouseService;
import com.skillstorm.inventory_management_backend.validators.StorageBinValidator;

@RestController
@RequestMapping("/storage-bin")
public class StorageBinController {

    private final StorageBinService storageBinService;
    private final WarehouseService warehouseService;

    public StorageBinController(StorageBinService storageBinService, WarehouseService warehouseService) {
        this.storageBinService = storageBinService;
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<StorageBin>> getAllStorageBins() {
        try {
            List<StorageBin> storageBins = storageBinService.findAllStorageBins();
            return new ResponseEntity<>(storageBins, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PostMapping
    public ResponseEntity<StorageBin> createStorageBin(@RequestBody StorageBin storageBin) {
        try {
            Warehouse warehouse = warehouseService.findWarehouseById(storageBin.getWarehouse().getId());
            List<String> activeLocations = storageBinService.findAllActiveStorageBinLocationsInWarehouse(warehouse);
            StorageBinValidator.validateStorageBin(storageBin, activeLocations);

            StorageBin createdStorageBin = storageBinService.createStorageBin(storageBin);
            return new ResponseEntity<>(createdStorageBin, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PutMapping
    public ResponseEntity<StorageBin> updateStorageBin(@RequestBody StorageBin storageBin) {
        try {
            Warehouse warehouse = warehouseService.findWarehouseById(storageBin.getWarehouse().getId());
            List<String> activeLocations = storageBinService.findAllActiveStorageBinLocationsInWarehouse(warehouse);
            StorageBinValidator.validateStorageBin(storageBin, activeLocations);

            StorageBin updatedStorageBin = storageBinService.saveStorageBin(storageBin);
            return new ResponseEntity<>(updatedStorageBin, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStorageBin(@PathVariable int id) {
        try {
            StorageBin storageBin = storageBinService.findStorageBinById(id);
            storageBinService.deleteStorageBin(storageBin);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
}
