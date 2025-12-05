package com.skillstorm.inventory_management_backend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skillstorm.inventory_management_backend.validators.WarehouseValidator;

public class WarehouseTest {

    /*
     * For a warehouse to be created:
     * Name needs to be added
     * - Name must contain at least 3 characters
     * - Name cannot be empty
     * Location needs to be added
     * - Location must include a country
     * - Location country name must contain at least 3 characters
     * - Country cannot be empty
     * - Locaiton must include a city
     * - Location city name must contain at least 3 characters
     * - City cannot be empty
     * - Location must include an address
     * - Address must contain at least 3 characters
     * - Address cannot be empty
     * - Location should include a state/region field
     * - State/Region must contain at least 3 characters
     * - State/Region cannot be empty
     * Maximum capacity must be added
     * - Max Capacity must be an integer value
     * - Max Capacity must be greater than 0
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
    @DisplayName("Country name has at least three characters")
    public void testCountryHasThreeCharacters() {
        assertFalse(WarehouseValidator.hasThreeCharacters(""));
        assertFalse(WarehouseValidator.hasThreeCharacters("US"));
        assertThrows(NullPointerException.class, () -> {
            WarehouseValidator.hasThreeCharacters(null);
        });
        assertTrue(WarehouseValidator.hasThreeCharacters("United States of America"));
    }

    @Test
    @DisplayName("Country is not null")
    public void testCountryIsNotNull() {
        assertThrows(NullPointerException.class, () -> {
            WarehouseValidator.notEmptyString(null);
        });
        assertTrue(WarehouseValidator.notEmptyString("United Kingdom"));
    }

    @Test
    @DisplayName("City name has at least three characters")
    public void testCityHasThreeCharacters() {
        assertFalse(WarehouseValidator.hasThreeCharacters(""));
        assertFalse(WarehouseValidator.hasThreeCharacters("aa"));
        assertThrows(NullPointerException.class, () -> {
            WarehouseValidator.hasThreeCharacters(null);
        });
        assertTrue(WarehouseValidator.hasThreeCharacters("Woodburn"));
    }

    @Test
    @DisplayName("City is not null")
    public void testCityIsNotNull() {
        assertThrows(NullPointerException.class, () -> {
            WarehouseValidator.notEmptyString(null);
        });
        assertTrue(WarehouseValidator.notEmptyString("Greenville"));
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
    @DisplayName("Location Address is not Null")
    public void testAddressIsNotNull() {
        assertThrows(NullPointerException.class, () -> {
            WarehouseValidator.notEmptyString(null);
        });
        assertTrue(WarehouseValidator.notEmptyString("1234 Test Rd"));
    }

    @Test
    @DisplayName("State or region has at least two characters")
    public void testStateOrRegionHasThreeCharacters() {
        assertFalse(WarehouseValidator.hasTwoCharacters(""));
        assertFalse(WarehouseValidator.hasTwoCharacters("a"));
        assertThrows(NullPointerException.class, () -> {
            WarehouseValidator.hasTwoCharacters(null);
        });
        assertTrue(WarehouseValidator.hasTwoCharacters("IN"));
        assertTrue(WarehouseValidator.hasTwoCharacters("Idiana"));
    }

    @Test
    @DisplayName("State or region is not Null")
    public void testStateOrRegionIsNotNull() {
        assertThrows(NullPointerException.class, () -> {
            WarehouseValidator.notEmptyString(null);
        });
        assertTrue(WarehouseValidator.notEmptyString("South Carolina"));
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
}
