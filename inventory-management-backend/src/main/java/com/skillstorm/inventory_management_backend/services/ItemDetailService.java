package com.skillstorm.inventory_management_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management_backend.models.ItemDetail;
import com.skillstorm.inventory_management_backend.repositories.ItemDetailRepository;

@Service
public class ItemDetailService {

    private final ItemDetailRepository itemDetailsRepository;

    public ItemDetailService(ItemDetailRepository itemDetailsRepository) {
        this.itemDetailsRepository = itemDetailsRepository;
    }

    public List<ItemDetail> findAllItemDetailss() {
        return itemDetailsRepository.findAll();
    }

    public ItemDetail findItemDetailsById(int id) {
        Optional<ItemDetail> ItemDetailsBin = itemDetailsRepository.findById(id);

        if (ItemDetailsBin.isPresent()) {
            return ItemDetailsBin.get();
        }
        return null;
    }

    public ItemDetail createItemDetails(ItemDetail itemDetails) {

        return itemDetailsRepository.save(itemDetails);
    }

    public ItemDetail saveItemDetails(ItemDetail itemDetails) {
        itemDetailsRepository.save(itemDetails);
        return itemDetails;
    }

    public ItemDetail deleteItemDetails(ItemDetail itemDetails) {
        itemDetailsRepository.deleteItemDetails(itemDetails.getId(), false);
        return itemDetails;
    }
}
