package com.skillstorm.inventory_management_backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.inventory_management_backend.models.StorageBin;
import com.skillstorm.inventory_management_backend.services.StorageBinService;

@RestController
@RequestMapping("/storage-bin")
@CrossOrigin({ "http://127.0.0.1:5500/" })
public class StorageBinController {

    private final StorageBinService storageBinService;

    public StorageBinController(StorageBinService storageBinService) {
        this.storageBinService = storageBinService;
    }

    @GetMapping
    public ResponseEntity<List<StorageBin>> getAllStorageBins() {
        try {
            List<StorageBin> storageBins = storageBinService.findAllStorageBins();
            return new ResponseEntity<>(storageBins, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @GetMapping("/bin")
    public ResponseEntity<StorageBin> getStorageBin(@RequestParam int id) {
        try {
            StorageBin storageBin = storageBinService.findStorageBinById(id);
            return new ResponseEntity<>(storageBin, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<StorageBin>> getAllActiveStorageBins(@PathVariable int warehouseId) {
        try {
            List<StorageBin> storageBins = storageBinService.findAllActiveStorageBinLocationsInWarehouse(warehouseId);
            return new ResponseEntity<>(storageBins, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PostMapping
    public ResponseEntity<StorageBin> createStorageBin(@RequestBody StorageBin storageBin,
            @RequestParam int warehouseId) {
        try {
            StorageBin createdStorageBin = storageBinService.createStorageBin(storageBin, warehouseId);
            return new ResponseEntity<>(createdStorageBin, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PutMapping
    public ResponseEntity<StorageBin> updateStorageBin(@RequestBody StorageBin storageBin) {
        try {
            StorageBin updatedStorageBin = storageBinService.updateStorageBin(storageBin);
            return new ResponseEntity<>(updatedStorageBin, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStorageBin(@PathVariable int id) {
        try {
            storageBinService.deleteStorageBin(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
}
