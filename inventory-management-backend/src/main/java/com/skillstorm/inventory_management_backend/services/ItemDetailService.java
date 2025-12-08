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

    public List<ItemDetail> findAllItemDetails() {
        return itemDetailsRepository.findAll();
    }

    public ItemDetail findItemDetailById(int id) {
        Optional<ItemDetail> ItemDetailsBin = itemDetailsRepository.findById(id);

        if (ItemDetailsBin.isPresent()) {
            return ItemDetailsBin.get();
        }
        return null;
    }

    public ItemDetail createItemDetail(ItemDetail itemDetails) {

        return itemDetailsRepository.save(itemDetails);
    }

    public ItemDetail saveItemDetail(ItemDetail itemDetails) {
        itemDetailsRepository.save(itemDetails);
        return itemDetails;
    }

    public ItemDetail deleteItemDetail(ItemDetail itemDetails) {
        itemDetailsRepository.deleteItemDetails(itemDetails.getId(), false);
        return itemDetails;
    }
}
