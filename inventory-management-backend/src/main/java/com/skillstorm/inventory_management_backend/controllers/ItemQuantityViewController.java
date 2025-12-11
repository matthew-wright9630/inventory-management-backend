package com.skillstorm.inventory_management_backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.inventory_management_backend.models.ItemQuantityView;
import com.skillstorm.inventory_management_backend.services.ItemQuantityViewService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/item-quantity")
public class ItemQuantityViewController {

    private final ItemQuantityViewService itemQuantityViewService;

    public ItemQuantityViewController(ItemQuantityViewService itemQuantityViewService) {
        this.itemQuantityViewService = itemQuantityViewService;
    }

    @GetMapping
    public List<ItemQuantityView> getAllItemQuantities() {
        return itemQuantityViewService.getAllQuantityView();
    }

    @GetMapping("/{id}")
    public ItemQuantityView getItemQuantityView(@PathVariable int id) {
        return itemQuantityViewService.getQuantityByItemDetailId(id);
    }

}
