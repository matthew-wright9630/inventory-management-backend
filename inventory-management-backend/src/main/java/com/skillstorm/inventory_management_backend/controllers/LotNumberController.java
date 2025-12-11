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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.inventory_management_backend.models.LotNumber;
import com.skillstorm.inventory_management_backend.services.LotNumberService;

@RestController
@RequestMapping("/lot-number")
public class LotNumberController {

    private final LotNumberService lotNumberService;

    public LotNumberController(LotNumberService lotNumberService) {
        this.lotNumberService = lotNumberService;
    }

    @GetMapping
    public ResponseEntity<List<LotNumber>> findAllLotNumbers() {
        try {
            List<LotNumber> lotNumbers = lotNumberService.findAllLotNumbers();
            return new ResponseEntity<>(lotNumbers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<LotNumber> getQuantityOfItem(@PathVariable int itemId) {
        try {
            LotNumber lotNumber = lotNumberService.findLotByItemId(itemId);
            return new ResponseEntity<>(lotNumber, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PostMapping
    public ResponseEntity<LotNumber> createLotNumber(@RequestBody LotNumber lotNumber, @RequestParam int itemId) {
        try {
            return new ResponseEntity<>(lotNumberService.createLotNumber(lotNumber, itemId), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PutMapping
    public ResponseEntity<LotNumber> updateLotNumber(@RequestBody LotNumber lotNumber,
            @RequestParam(defaultValue = "0") int itemId) {
        try {
            LotNumber newLotNumber = lotNumberService.saveLotNumber(lotNumber, itemId);
            return new ResponseEntity<LotNumber>(newLotNumber, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message",
                    e.getMessage()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLotNumber(@PathVariable int id) {
        try {
            lotNumberService.deleteLotNumber(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
}
