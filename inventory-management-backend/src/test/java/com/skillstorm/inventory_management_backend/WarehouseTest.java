package com.skillstorm.inventory_management_backend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skillstorm.inventory_management_backend.models.Location;
import com.skillstorm.inventory_management_backend.validators.WarehouseValidator;

public class WarehouseTest {

    /*
     * For a warehouse to be created:
     * Name needs to be added
     * - Name must contain at least 3 characters
     * - Name cannot be empty
     * Address needs to be added
     * - Address must contain at least 3 characters
     * - Address cannot be empty
     * Address Line Two needs to be added
     * - Address Line Two must contain at least 3 characters
     * - Address Line Two cannot be empty
     * Maximum capacity must be added
     * - Max Capacity must be an integer value
     * - Max Capacity must be greater than 0
     * Location cannot be empty
     */

    // Creation Tests
    @Test
    @DisplayName("Name has at least three characters")
    public void testNameHasThreeCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseValidator.hasThreeCharacters("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseValidator.hasThreeCharacters("US");
        });
        assertTrue(WarehouseValidator.notEmptyString("Warehouse 1"));
    }

    @Test
    @DisplayName("Name is not null")
    public void testNameIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseValidator.notEmptyString(null);
        });
        assertTrue(WarehouseValidator.notEmptyString("Warehouse 1"));
    }

    @Test
    @DisplayName("Address has at least three characters")
    public void testAddressHasThreeCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseValidator.hasThreeCharacters("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseValidator.hasThreeCharacters("TT");
        });
        assertTrue(WarehouseValidator.hasThreeCharacters("987 Testing Ave."));
    }

    @Test
    @DisplayName("Address is not Null")
    public void testAddressIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseValidator.notEmptyString(null);
        });
        assertTrue(WarehouseValidator.notEmptyString("1234 Test Rd"));
    }

    @Test
    @DisplayName("Address Line Two has at least three characters")
    public void testAddressLineTwoHasThreeCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseValidator.hasThreeCharacters("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseValidator.hasThreeCharacters("aa");
        });
        assertTrue(WarehouseValidator.hasThreeCharacters("987 Testing Ave."));
    }

    @Test
    @DisplayName("Address Line Two is not Null")
    public void testAddressLineTwoIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseValidator.notEmptyString(null);
        });
        assertTrue(WarehouseValidator.notEmptyString("1234 Test Rd"));
    }

    @Test
    @DisplayName("Max Capacity is an integer")
    public void testMaxCapacityIsInteger() {
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseValidator.inputIsInteger("Test");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseValidator.inputIsInteger("15.5");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseValidator.inputIsInteger("true");
        });
        assertTrue(WarehouseValidator.inputIsInteger("10000"));
    }

    @Test
    @DisplayName("Maximum Capacity is greater than zero")
    public void testMaxCapacityIsGreaterThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseValidator.greaterThanZero(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseValidator.greaterThanZero(-100);
        });
        assertTrue(WarehouseValidator.greaterThanZero(1000));
    }

    @Test
    @DisplayName("Location is not empty")
    public void testLocationIsNotEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseValidator.notEmptyString(null);
        });
        Location location = new Location();
        assertFalse(WarehouseValidator.locationIsNotEmpty(location));
        location.setId(5);
        assertTrue(WarehouseValidator.locationIsNotEmpty(location));
    }
}
