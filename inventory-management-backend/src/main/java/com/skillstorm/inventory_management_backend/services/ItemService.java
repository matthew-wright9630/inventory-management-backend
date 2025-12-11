package com.skillstorm.inventory_management_backend.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.InternalException;
import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management_backend.models.Item;
import com.skillstorm.inventory_management_backend.models.ItemDetail;
import com.skillstorm.inventory_management_backend.models.StorageBin;
import com.skillstorm.inventory_management_backend.repositories.ItemRepository;
import com.skillstorm.inventory_management_backend.validators.ItemValidator;

@Service
public class ItemService {

    private final ItemRepository itemsRepository;
    private final StorageBinService storageBinService;
    private final ItemDetailService itemDetailService;

    public ItemService(ItemRepository itemsRepository, StorageBinService storageBinService,
            ItemDetailService itemDetailService) {
        this.itemsRepository = itemsRepository;
        this.storageBinService = storageBinService;
        this.itemDetailService = itemDetailService;
    }

    public List<Item> findAllItems() {
        return itemsRepository.findAll();
    }

    public Item findItemById(int id) {
        Optional<Item> item = itemsRepository.findById(id);

        if (item.isPresent()) {
            return item.get();
        }
        throw new IllegalArgumentException("Item does not exist. Please try with another item.");
    }

    public List<Item> findItemsByStorageBin(int storageBinId) {
        StorageBin storageBin = storageBinService.findStorageBinById(storageBinId);
        return itemsRepository.findAllByStorageBin(storageBin);
    }

    public Item createItem(Item item, int storageBinId, int itemDetailId) {
        StorageBin storageBin = storageBinService.findStorageBinById(storageBinId);
        ItemDetail itemDetail = itemDetailService.findItemDetailById(itemDetailId);
        item.setStorageBin(storageBin);
        item.setItemDetail(itemDetail);
        if (ItemValidator.validateItem(item)) {
            return itemsRepository.save(item);
        }
        throw new IllegalArgumentException("Item was not able to be created");
    }

    public Item saveItem(Item item) {
        if (item.getStorageBin().getId() <= 0) {
            throw new IllegalArgumentException("Storage bin does not exist. Please try with another storage bin.");
        }
        if (item.getItemDetail().getId() <= 0) {
            throw new IllegalArgumentException("Item detail does not exist. Please try with another item detail.");
        }
        if (ItemValidator.validateItem(item)) {
            return itemsRepository.save(item);
        }
        throw new InternalException("Item was not able to be created");
    }

    public Item deleteItem(int id) {
        Item itemToBeDeleted = findItemById(id);
        if (itemToBeDeleted.getId() <= 0) {
            throw new IllegalArgumentException("Item does not exist. Please try with another item.");
        }
        itemsRepository.deleteItem(id, false);
        return itemToBeDeleted;
    }
}
