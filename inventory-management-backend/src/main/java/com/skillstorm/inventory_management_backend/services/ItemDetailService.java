package com.skillstorm.inventory_management_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management_backend.models.ItemDetail;
import com.skillstorm.inventory_management_backend.repositories.ItemDetailRepository;
import com.skillstorm.inventory_management_backend.validators.ItemDetailValidator;

@Service
public class ItemDetailService {

    private final ItemDetailRepository itemDetailsRepository;

    public ItemDetailService(ItemDetailRepository itemDetailsRepository) {
        this.itemDetailsRepository = itemDetailsRepository;
    }

    public List<ItemDetail> findAllItemDetails() {
        return itemDetailsRepository.findAll();
    }

    public ItemDetail findItemDetailById(int id) {
        Optional<ItemDetail> itemDetails = itemDetailsRepository.findById(id);

        if (itemDetails.isPresent()) {
            return itemDetails.get();
        }
        throw new IllegalArgumentException("Item Details does not exist. Please try with another item detail.");
    }

    public ItemDetail findItemDetailByName(String name) {
        return itemDetailsRepository.findByName(name);
    }

    public ItemDetail createItemDetail(ItemDetail itemDetail) {
        if (ItemDetailValidator.validateItemDetails(itemDetail)) {
            return itemDetailsRepository.save(itemDetail);
        }
        throw new IllegalArgumentException("Values were not input as expected. input: " + itemDetail);
    }

    public ItemDetail saveItemDetail(ItemDetail itemDetail) {
        ItemDetail foundItemDetail = findItemDetailById(itemDetail.getId());
        if (foundItemDetail.getId() <= 0) {
            throw new IllegalArgumentException("Item Details does not exist.");
        }
        if (ItemDetailValidator.validateItemDetails(itemDetail)) {
            return itemDetailsRepository.save(itemDetail);
        }
        throw new IllegalArgumentException("Validation failed. Please try again with different inputs.");
    }

    public int deleteItemDetail(int id) {
        ItemDetail foundItemDetail = findItemDetailById(id);
        if (foundItemDetail.getId() <= 0) {
            throw new IllegalArgumentException("Item Details does not exist.");
        }
        return itemDetailsRepository.deleteItemDetails(id, false);
    }
}
