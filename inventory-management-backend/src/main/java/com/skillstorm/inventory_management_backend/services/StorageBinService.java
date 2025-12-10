package com.skillstorm.inventory_management_backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management_backend.models.StorageBin;
import com.skillstorm.inventory_management_backend.models.Warehouse;
import com.skillstorm.inventory_management_backend.repositories.StorageBinRepository;
import com.skillstorm.inventory_management_backend.validators.StorageBinValidator;

@Service
public class StorageBinService {
    private final StorageBinRepository storageBinRepository;
    private final WarehouseService warehouseService;

    public StorageBinService(StorageBinRepository storageBinRepository, WarehouseService warehouseService) {
        this.storageBinRepository = storageBinRepository;
        this.warehouseService = warehouseService;
    }

    public List<StorageBin> findAllStorageBins() {
        return storageBinRepository.findAll();
    }

    public StorageBin findStorageBinById(int id) {
        Optional<StorageBin> storageBin = storageBinRepository.findById(id);

        if (storageBin.isPresent()) {
            return storageBin.get();
        }
        throw new IllegalArgumentException("Storage bin does not exist. Please try with another storage bin.");
    }

    public List<StorageBin> findAllActiveStorageBinLocationsInWarehouse(int warehouseId) {
        Warehouse warehouse = warehouseService.findWarehouseById(warehouseId);
        if (warehouse.getId() <= 0) {
            throw new IllegalArgumentException(
                    "Warehouse does not exist. Please try again with another warehouse");
        }
        List<StorageBin> activeStorageBins = storageBinRepository.findByWarehouseIdAndIsActive(warehouseId, true);
        List<StorageBin> listOfActiveStorageBins = new ArrayList<>();
        for (StorageBin bin : activeStorageBins) {
            listOfActiveStorageBins.add(bin);
        }
        return listOfActiveStorageBins;
    }

    public StorageBin createStorageBin(StorageBin storageBin, int warehouseId) {
        Warehouse warehouse = warehouseService.findWarehouseById(warehouseId);
        if (warehouse.getId() <= 0) {
            throw new IllegalArgumentException(
                    "Warehouse does not exist. Please try again with another warehouse");
        }
        if (!warehouse.isActive()) {
            throw new IllegalArgumentException(
                    "Cannot place storage bin in an inactive warehouse. Please select an active warehouse.");
        }
        storageBin.setWarehouse(warehouse);
        List<StorageBin> activeStorageBins = findAllActiveStorageBinLocationsInWarehouse(
                warehouseId);
        if (StorageBinValidator.validateStorageBin(storageBin,
                activeStorageBins)) {
            return storageBinRepository.save(storageBin);
        }
        throw new IllegalArgumentException("Storage bin not able to be created");
    }

    public StorageBin updateStorageBin(StorageBin storageBin) {
        StorageBin foundStorageBin = findStorageBinById(storageBin.getId());
        if (foundStorageBin.getId() <= 0) {
            throw new IllegalArgumentException("Storage bin does not exist. Please try with another storage bin.");
        }
        List<StorageBin> activeStorageBins = findAllActiveStorageBinLocationsInWarehouse(
                storageBin.getWarehouse().getId());
        if (StorageBinValidator.validateStorageBin(storageBin,
                activeStorageBins)) {
            return storageBinRepository.save(storageBin);
        }
        throw new IllegalArgumentException("Storage bin not able to be created");
    }

    public StorageBin deleteStorageBin(int id) {
        StorageBin foundStorageBin = findStorageBinById(id);
        if (foundStorageBin.getId() <= 0) {
            throw new IllegalArgumentException("Storage bin does not exist. Please try with another storage bin.");
        }
        storageBinRepository.deleteStorageBin(foundStorageBin.getId(), false);
        return foundStorageBin;
    }
}
