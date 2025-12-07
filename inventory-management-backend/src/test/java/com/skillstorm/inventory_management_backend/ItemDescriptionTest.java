package com.skillstorm.inventory_management_backend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skillstorm.inventory_management_backend.models.Location;
import com.skillstorm.inventory_management_backend.validators.ItemDescriptionValidator;

public class ItemDescriptionTest {

    /**
     * Item description must include a name
     * - Name must not be empty
     * - Name must be at least 3 characters long
     * Item description must include a SKU
     * - SKU must not be empty
     * Item description must include a description
     * - Description must not be empty
     * - Description must be at least 3 characters long
     * Item description can include an optional shelf-life
     * - If shelf-life is added, date must be greater than 0 days.
     */

    @Test
    @DisplayName("Item Description Name has at least three characters")
    public void testNameHasThreeCharacters() {
        assertFalse(ItemDescriptionValidator.hasThreeCharacters(""));
        assertFalse(ItemDescriptionValidator.hasThreeCharacters("IT"));
        assertTrue(ItemDescriptionValidator.notEmptyString("Item description 1"));
    }

    @Test
    @DisplayName("Item Description Name is not null")
    public void testNameIsNotNull() {
        assertThrows(NullPointerException.class, () -> {
            ItemDescriptionValidator.notEmptyString(null);
        });
        assertTrue(ItemDescriptionValidator.notEmptyString("New item description"));
    }

    @Test
    @DisplayName("SKU number is not null")
    public void testSKUIsNotNull() {
        assertThrows(NullPointerException.class, () -> {
            ItemDescriptionValidator.notEmptyString(null);
        });
        assertTrue(ItemDescriptionValidator.notEmptyString("1"));
        assertTrue(ItemDescriptionValidator.notEmptyString("34562-x5-433"));
    }

    @Test
    @DisplayName("ItemDescription description field has at least three characters")
    public void testDescriptionHasThreeCharacters() {
        assertFalse(ItemDescriptionValidator.hasThreeCharacters(""));
        assertFalse(ItemDescriptionValidator.hasThreeCharacters("aa"));
        assertThrows(NullPointerException.class, () -> {
            ItemDescriptionValidator.hasThreeCharacters(null);
        });
        assertTrue(ItemDescriptionValidator
                .hasThreeCharacters("This is a description of a test item. A real item does not exist."));
    }

    @Test
    @DisplayName("ItemDescription description field is not Null")
    public void testDescriptionIsNotNull() {
        assertThrows(NullPointerException.class, () -> {
            ItemDescriptionValidator.notEmptyString(null);
        });
        assertTrue(ItemDescriptionValidator.notEmptyString("Test Description"));
    }

    @Test
    @DisplayName("Shelf-life is an integer")
    public void testShelfLifeIsInteger() {
        assertThrows(NumberFormatException.class, () -> {
            ItemDescriptionValidator.inputIsInteger("Test");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDescriptionValidator.inputIsInteger("15.5");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDescriptionValidator.inputIsInteger("true");
        });
        assertTrue(ItemDescriptionValidator.inputIsInteger("10000"));
    }

    @Test
    @DisplayName("Shelf life is greater than zero")
    public void testShelfLifeIsGreaterThanZero() {
        assertFalse(ItemDescriptionValidator.greaterThanZero(0));
        assertFalse(ItemDescriptionValidator.greaterThanZero(-100));
        assertTrue(ItemDescriptionValidator.greaterThanZero(1000));
    }
}
