package com.skillstorm.inventory_management_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management_backend.models.ItemQuantityView;
import com.skillstorm.inventory_management_backend.repositories.ItemQuantityViewRepository;

@Service
public class ItemQuantityViewService {

    private final ItemQuantityViewRepository itemQuantityViewRepository;

    public ItemQuantityViewService(ItemQuantityViewRepository itemQuantityViewRepository) {
        this.itemQuantityViewRepository = itemQuantityViewRepository;
    }

    public List<ItemQuantityView> getAllQuantityView() {
        return itemQuantityViewRepository.findAll();
    }

    public ItemQuantityView getQuantityByItemDetailId(int id) {
        return itemQuantityViewRepository.findById(id);
    }
}
