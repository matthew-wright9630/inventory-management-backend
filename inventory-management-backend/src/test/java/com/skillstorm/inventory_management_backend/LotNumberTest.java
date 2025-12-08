package com.skillstorm.inventory_management_backend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skillstorm.inventory_management_backend.models.Item;
import com.skillstorm.inventory_management_backend.validators.LotNumberValidator;

public class LotNumberTest {

    /**
     * Quantity needs to be added
     * - Quantity has to be larger than 0
     * - Quantity must be an integer
     * ManufactureDate must be added to the request
     * ManufactureDate must be in a LocalDate format.
     * Item must be added to the request
     */

    @Test
    @DisplayName("Quantity is an integer")
    public void testQuantityIsInteger() {
        assertThrows(IllegalArgumentException.class, () -> {
            LotNumberValidator.inputIsInteger("Test");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            LotNumberValidator.inputIsInteger("15.5");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            LotNumberValidator.inputIsInteger("true");
        });
        assertTrue(LotNumberValidator.inputIsInteger("10000"));
    }

    @Test
    @DisplayName("Quantity is greater than zero")
    public void testQuantityIsGreaterThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            LotNumberValidator.greaterThanZero(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            LotNumberValidator.greaterThanZero(-100);
        });
        assertTrue(LotNumberValidator.greaterThanZero(1000));
    }

    @Test
    @DisplayName("Manufacture date is not null")
    public void testManufactureDateIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            LotNumberValidator.notEmptyString(null);
        });
        assertTrue(LotNumberValidator.notEmptyString("Test Description"));
    }

    @Test
    @DisplayName("Manufactured date is in a LocalDate format")
    public void testManufactureDateIsInDateFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            LotNumberValidator.verifyDateFormat("March 3, 2025");
        });
        assertTrue(LotNumberValidator.verifyDateFormat("2025-12-08"));
    }

    @Test
    @DisplayName("Item is not empty")
    public void testItemIsNotEmpty() {
        assertThrows(NullPointerException.class, () -> {
            LotNumberValidator.itemIsNotEmpty(null);
        });
        Item item = new Item();
        assertFalse(LotNumberValidator.itemIsNotEmpty(item));
        item.setId(5);
        assertTrue(LotNumberValidator.itemIsNotEmpty(item));
    }
}
