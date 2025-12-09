package com.skillstorm.inventory_management_backend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skillstorm.inventory_management_backend.models.StorageBin;
import com.skillstorm.inventory_management_backend.models.Warehouse;
import com.skillstorm.inventory_management_backend.validators.StorageBinValidator;

public class StorageBinTest {

    /**
     * Storage bin must include a warehouse
     * Storage bin must include a storage location in the warehouse
     * - Storage warehouse cannot be empty
     * A storage bin cannot share a storage location with another active storage bin
     */

    @Test
    @DisplayName("Warehouse is not empty")
    public void testWarehouseIsNotEmpty() {
        assertThrows(NullPointerException.class, () -> {
            StorageBinValidator.warehouseIsNotEmpty(null);
        });
        Warehouse warehouse = new Warehouse();
        assertFalse(StorageBinValidator.warehouseIsNotEmpty(warehouse));
        warehouse.setId(5);
        assertTrue(StorageBinValidator.warehouseIsNotEmpty(warehouse));
    }

    @Test
    @DisplayName("Storage bin location is not empty")
    public void testStorageBinLocationIsNotEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            StorageBinValidator.notEmptyString(null);
        });
        assertTrue(StorageBinValidator.notEmptyString("1234 Test Rd"));
    }

    @Test
    @DisplayName("Storage bins that share a warehouse are not able to share a storage location.")
    public void testStorageBinDoesNotShareLocationWithActiveStorageBin() {
        List<String> locations = new ArrayList<>();
        locations.add("1A");
        locations.add("1B");
        StorageBin storageBin1 = new StorageBin();
        storageBin1.setStorageLocation("1B");
        StorageBin storageBin2 = new StorageBin();
        storageBin2.setStorageLocation("1A");
        StorageBin storageBin3 = new StorageBin();
        storageBin3.setStorageLocation("1C");
        assertThrows(IllegalArgumentException.class, () -> {
            StorageBinValidator.storageBinLocationIsUnique(locations, storageBin1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            StorageBinValidator.storageBinLocationIsUnique(locations, storageBin2);
        });
        assertTrue(StorageBinValidator.storageBinLocationIsUnique(locations, storageBin3));
    }
}
