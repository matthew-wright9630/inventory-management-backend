package com.skillstorm.inventory_management_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.inventory_management_backend.models.Item;
import com.skillstorm.inventory_management_backend.repositories.ItemRepository;
import com.skillstorm.inventory_management_backend.validators.ItemValidator;

@RestController
public class ItemService {

    private final ItemRepository itemsRepository;

    public ItemService(ItemRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Item> findAllItems() {
        return itemsRepository.findAll();
    }

    public Item findItemById(int id) {
        Optional<Item> item = itemsRepository.findById(id);

        if (item.isPresent()) {
            return item.get();
        }
        return null;
    }

    public Item createItem(Item item) {
        ItemValidator.validateItem(item);
        return itemsRepository.save(item);
    }

    public Item saveItem(Item item) {
        ItemValidator.validateItem(item);
        itemsRepository.save(item);
        return item;
    }

    public Item deleteItem(Item item) {
        itemsRepository.deleteItem(item.getId(), false);
        return item;
    }
}
