package com.skillstorm.inventory_management_backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management_backend.models.Location;
import com.skillstorm.inventory_management_backend.models.Warehouse;
import com.skillstorm.inventory_management_backend.repositories.WarehouseRepository;
import com.skillstorm.inventory_management_backend.validators.WarehouseValidator;

/**
 * Service class for the Warehouse entity that handles the business logic for
 * creating, reading, updating, and deleting Warehouse entities.
 */
@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final LocationService locationService;

    /**
     * Injects the warehouseRepository and locationService variables.
     * 
     * @param warehouseRepository Handles the queries to the warehouse table in the
     *                            database.
     * @param locationService     Used for logic of finding locations.
     */
    public WarehouseService(WarehouseRepository warehouseRepository,
            LocationService locationService) {
        this.warehouseRepository = warehouseRepository;
        this.locationService = locationService;
    }

    /**
     * Retrieves all warehouses
     * 
     * @return all warehouses in the database
     */
    public List<Warehouse> findAllWarehouses() {
        return warehouseRepository.findAll();
    }

    /**
     * Retrieves a warehouse by its ID value
     * 
     * @param id the ID of the warehouse to receive
     * @return the warehouse with the given ID
     * @throws IllegalArgumentException if no warehouse exists with the given ID
     */
    public Warehouse findWarehouseById(int id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);
        if (warehouse.isPresent()) {
            return warehouse.get();
        }
        throw new IllegalArgumentException("Warehouse does not exist. Please try with another warehouse.");
    }

    /**
     *
     * Creates a warehouse and links it to a specified location.
     * Validates the warehouse details and that the location exists
     * 
     * @param warehouse  The warehouse details
     * @param locationId The primary id of the location that the warehouse should be
     *                   linked to.
     * @return a warehouse entity that is created in the database
     * @throws IllegalArgumentException if the location does not exist in the
     *                                  database or if the validateWarehouse method
     *                                  fails.
     */
    public Warehouse createWarehouse(Warehouse warehouse, int locationId) {
        Location location = locationService.findLocationById(locationId);
        if (location.getId() <= 0) {
            throw new IllegalArgumentException("Location does not exist. Please try with another location.");
        }
        warehouse.setLocation(location);
        if (WarehouseValidator.validateWarehouse(warehouse)) {
            return warehouseRepository.save(warehouse);
        }
        throw new IllegalArgumentException("Values were not input as expected. input: " + warehouse);
    }

    /**
     * Retrieves a warehouse by its percent capacity
     * 
     * @param capacityPercent The percent full a warehouse should be to be
     *                        retrieved.
     * @return All warehouses with a capacity greater than or equal to given
     *         percentage.
     */
    public List<Warehouse> findWarehousesByCapacityLeft(int capacityPercent) {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        List<Warehouse> returnedWarehouses = new ArrayList<Warehouse>();
        for (Warehouse w : warehouses) {
            returnedWarehouses.add(w);
            // find all storage_bin where w.id == warehouse.id
            // if storageBin.length >= capacityPercent, returnedWarehouses.add(w);
        }
        return returnedWarehouses;
    }

    /**
     * Updates a specified warehouse
     * 
     * @param warehouse is the warehouse to update
     * @return the updated warehouse
     * @throws IllegalArgumentException if the location does not exist or if the
     *                                  warehouse does not exist.
     */
    public Warehouse saveWarehouse(Warehouse warehouse) {

        Location location = warehouse.getLocation();
        if (location.getId() <= 0) {
            throw new IllegalArgumentException("Location does not exist. Please try with another location.");
        }
        if (warehouse.getId() > 0) {
            warehouse.setLocation(location);
            WarehouseValidator.validateWarehouse(warehouse);
            warehouseRepository.save(warehouse);
            return warehouse;
        }
        throw new IllegalArgumentException("Warehouse does not exist. Please try with another warehouse.");
    }

    /**
     * Updates a warehouse and its location.
     * 
     * @param warehouse  is the warehouse to update
     * @param locationId the id of the location that the warehouse should be updated
     *                   to
     * @return the updated warehouse
     * @throws IllegalArgumentException if the location does not exist or if the
     *                                  warehouse does not exist.
     */
    public Warehouse saveWarehouse(Warehouse warehouse, int locationId) {

        Location location = locationService.findLocationById(locationId);
        if (location.getId() <= 0) {
            throw new IllegalArgumentException("Location does not exist. Please try with another location.");
        }
        Warehouse existingWarehouse = findWarehouseById(warehouse.getId());
        if (warehouse.getId() > 0) {
            existingWarehouse.setLocation(location);
            existingWarehouse.setName(warehouse.getName());
            existingWarehouse.setMaximumCapacity(warehouse.getMaximumCapacity());
            existingWarehouse.setAddress(warehouse.getAddress());
            existingWarehouse.setAddressLineTwo(warehouse.getAddressLineTwo());
            existingWarehouse.setActive(warehouse.isActive());
            WarehouseValidator.validateWarehouse(warehouse);
            warehouseRepository.save(warehouse);
            return warehouse;
        }
        throw new IllegalArgumentException("Warehouse does not exist. Please try with another warehouse.");
    }

    /**
     * Updates the isActive field of the warehouse to false
     * 
     * @param id The ID of the warehouse to be deleted.
     * @return The warehouse that was deleted.
     * @throws IllegalArgumentException if the warehouse with the given ID is not
     *                                  found.
     */
    public Warehouse deleteWarehouse(int id) {
        Warehouse foundWarehouse = findWarehouseById(id);
        if (foundWarehouse.getId() > 0) {
            warehouseRepository.deleteWarehouse(foundWarehouse.getId(), false);
            return foundWarehouse;
        }
        throw new IllegalArgumentException("Warehouse does not exist. Please try with another location.");
    }
}
