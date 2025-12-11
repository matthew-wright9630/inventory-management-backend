package com.skillstorm.inventory_management_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management_backend.models.Item;
import com.skillstorm.inventory_management_backend.models.LotNumber;
import com.skillstorm.inventory_management_backend.repositories.LotNumberRepository;
import com.skillstorm.inventory_management_backend.validators.LotNumberValidator;

@Service
public class LotNumberService {
    private final LotNumberRepository lotNumbersRepository;
    private final ItemService itemService;

    public LotNumberService(LotNumberRepository lotNumbersRepository, ItemService itemService) {
        this.lotNumbersRepository = lotNumbersRepository;
        this.itemService = itemService;
    }

    public List<LotNumber> findAllLotNumbers() {
        return lotNumbersRepository.findAll();
    }

    public LotNumber findLotNumberById(int id) {
        Optional<LotNumber> LotNumber = lotNumbersRepository.findById(id);

        if (LotNumber.isPresent()) {
            return LotNumber.get();
        }
        throw new IllegalArgumentException("Lot number does not exist. Please try with another lot number.");
    }

    public LotNumber findLotByItemId(int itemId) {
        LotNumber lotNumber = lotNumbersRepository.findByItemId(itemId);
        return lotNumber;
    }

    public LotNumber createLotNumber(LotNumber lotNumber, int itemId) {
        Item item = itemService.findItemById(itemId);
        lotNumber.setItem(item);
        if (LotNumberValidator.validateLotNumber(lotNumber)) {
            return lotNumbersRepository.save(lotNumber);
        }
        throw new IllegalArgumentException("Lot Number not able to be created");
    }

    public LotNumber saveLotNumber(LotNumber lotNumber, int itemId) {
        if (itemId != 0) {
            Item item = itemService.findItemById(itemId);
            lotNumber.setItem(item);
        }
        findLotNumberById(lotNumber.getId());
        lotNumbersRepository.save(lotNumber);
        return lotNumber;
    }

    public LotNumber deleteLotNumber(int id) {
        LotNumber lotNumberToDelete = findLotNumberById(id);
        lotNumbersRepository.deleteLotNumber(id, false);
        return lotNumberToDelete;
    }
}
