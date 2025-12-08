package com.skillstorm.inventory_management_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management_backend.models.LotNumber;
import com.skillstorm.inventory_management_backend.repositories.LotNumberRepository;

@Service
public class LotNumberService {
    private final LotNumberRepository lotNumbersRepository;

    public LotNumberService(LotNumberRepository lotNumbersRepository) {
        this.lotNumbersRepository = lotNumbersRepository;
    }

    public List<LotNumber> findAllLotNumbers() {
        return lotNumbersRepository.findAll();
    }

    public LotNumber findLotNumberById(int id) {
        Optional<LotNumber> LotNumbersBin = lotNumbersRepository.findById(id);

        if (LotNumbersBin.isPresent()) {
            return LotNumbersBin.get();
        }
        return null;
    }

    public LotNumber createLotNumber(LotNumber lotNumbers) {

        return lotNumbersRepository.save(lotNumbers);
    }

    public LotNumber saveLotNumber(LotNumber lotNumbers) {
        lotNumbersRepository.save(lotNumbers);
        return lotNumbers;
    }

    public LotNumber deleteLotNumber(LotNumber lotNumbers) {
        lotNumbersRepository.deleteLotNumber(lotNumbers.getId(), false);
        return lotNumbers;
    }
}
