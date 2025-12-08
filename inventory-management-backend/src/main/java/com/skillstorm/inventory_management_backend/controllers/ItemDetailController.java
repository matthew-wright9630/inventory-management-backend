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

import com.skillstorm.inventory_management_backend.models.ItemDetail;
import com.skillstorm.inventory_management_backend.services.ItemDetailService;
import com.skillstorm.inventory_management_backend.validators.ItemDetailValidator;

@RestController
@RequestMapping("/item-detail")
public class ItemDetailController {

    private final ItemDetailService itemDetailService;

    public ItemDetailController(ItemDetailService itemDetailService) {
        this.itemDetailService = itemDetailService;
    }

    @GetMapping
    public ResponseEntity<List<ItemDetail>> findAllItemDetails() {
        try {
            List<ItemDetail> itemDetails = itemDetailService.findAllItemDetails();
            return new ResponseEntity<>(itemDetails, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PostMapping
    public ResponseEntity<ItemDetail> createItemDetail(@RequestBody ItemDetail itemDetail) {
        try {
            ItemDetailValidator.validateItemDetails(itemDetail);
            return new ResponseEntity<>(itemDetailService.saveItemDetail(itemDetail), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PutMapping
    public ResponseEntity<ItemDetail> updateItemDetail(@RequestBody ItemDetail itemDetail) {
        try {
            ItemDetailValidator.validateItemDetails(itemDetail);
            ItemDetail newItemDetail = itemDetailService.saveItemDetail(itemDetail);
            return new ResponseEntity<ItemDetail>(newItemDetail, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemDetail(@PathVariable int id) {
        try {
            ItemDetail itemDetail = itemDetailService.findItemDetailById(id);
            itemDetailService.deleteItemDetail(itemDetail);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
}
