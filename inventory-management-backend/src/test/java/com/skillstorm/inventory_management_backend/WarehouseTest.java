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
        assertFalse(WarehouseValidator.hasThreeCharacters(""));
        assertFalse(WarehouseValidator.hasThreeCharacters("Hi"));
        assertTrue(WarehouseValidator.notEmptyString("Warehouse 1"));
    }

    @Test
    @DisplayName("Name is not null")
    public void testNameIsNotNull() {
        assertThrows(NullPointerException.class, () -> {
            WarehouseValidator.notEmptyString(null);
        });
        assertTrue(WarehouseValidator.notEmptyString("Warehouse 1"));
    }

    @Test
    @DisplayName("Address has at least three characters")
    public void testAddressHasThreeCharacters() {
        assertFalse(WarehouseValidator.hasThreeCharacters(""));
        assertFalse(WarehouseValidator.hasThreeCharacters("aa"));
        assertThrows(NullPointerException.class, () -> {
            WarehouseValidator.hasThreeCharacters(null);
        });
        assertTrue(WarehouseValidator.hasThreeCharacters("987 Testing Ave."));
    }

    @Test
    @DisplayName("Address is not Null")
    public void testAddressIsNotNull() {
        assertThrows(NullPointerException.class, () -> {
            WarehouseValidator.notEmptyString(null);
        });
        assertTrue(WarehouseValidator.notEmptyString("1234 Test Rd"));
    }

    @Test
    @DisplayName("Address Line Two has at least three characters")
    public void testAddressLineTwoHasThreeCharacters() {
        assertFalse(WarehouseValidator.hasThreeCharacters(""));
        assertFalse(WarehouseValidator.hasThreeCharacters("aa"));
        assertThrows(NullPointerException.class, () -> {
            WarehouseValidator.hasThreeCharacters(null);
        });
        assertTrue(WarehouseValidator.hasThreeCharacters("987 Testing Ave."));
    }

    @Test
    @DisplayName("Address Line Two is not Null")
    public void testAddressLineTwoIsNotNull() {
        assertThrows(NullPointerException.class, () -> {
            WarehouseValidator.notEmptyString(null);
        });
        assertTrue(WarehouseValidator.notEmptyString("1234 Test Rd"));
    }

    @Test
    @DisplayName("Max Capacity is an integer")
    public void testMaxCapacityIsInteger() {
        assertThrows(NumberFormatException.class, () -> {
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
        assertFalse(WarehouseValidator.greaterThanZero(0));
        assertFalse(WarehouseValidator.greaterThanZero(-100));
        assertTrue(WarehouseValidator.greaterThanZero(1000));
    }

    @Test
    @DisplayName("Location is not empty")
    public void testLocationIsNotEmpty() {
        assertThrows(NullPointerException.class, () -> {
            WarehouseValidator.locationIsNotEmpty(null);
        });
        Location location = new Location();
        assertFalse(WarehouseValidator.locationIsNotEmpty(location));
        location.setId(5);
        assertTrue(WarehouseValidator.locationIsNotEmpty(location));
    }
}
