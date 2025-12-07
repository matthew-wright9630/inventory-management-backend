package com.skillstorm.inventory_management_backend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.mapping.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skillstorm.inventory_management_backend.models.Item;
import com.skillstorm.inventory_management_backend.models.ItemDetail;
import com.skillstorm.inventory_management_backend.validators.ItemDetailValidator;

public class ItemDetailTest {

    /**
     * Item details must include a name
     * - Name must not be empty
     * - Name must be at least 3 characters long
     * Item details must include a SKU
     * - SKU must not be empty
     * Item details must include a description
     * - Description must not be empty
     * - Description must be at least 3 characters long
     * Item details can include an optional shelf-life
     * - If shelf-life is added, date must be greater than 0 days.
     */

    @Test
    @DisplayName("Item Details Name has at least three characters")
    public void testNameHasThreeCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailValidator.hasThreeCharacters("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailValidator.hasThreeCharacters("IT");
        });
        assertTrue(ItemDetailValidator.notEmptyString("Item details 1"));
    }

    @Test
    @DisplayName("Item Details Name is not null")
    public void testNameIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailValidator.notEmptyString(null);
        });
        assertTrue(ItemDetailValidator.notEmptyString("New item details"));
    }

    @Test
    @DisplayName("SKU number is not null")
    public void testSKUIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailValidator.notEmptyString(null);
        });
        assertTrue(ItemDetailValidator.notEmptyString("1"));
        assertTrue(ItemDetailValidator.notEmptyString("34562-x5-433"));
    }

    @Test
    @DisplayName("Item description field has at least three characters")
    public void testDescriptionHasThreeCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailValidator.hasThreeCharacters("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailValidator.hasThreeCharacters("aa");
        });
        assertTrue(ItemDetailValidator
                .hasThreeCharacters("This is a details of a test item. A real item does not exist."));
    }

    @Test
    @DisplayName("Item description field is not Null")
    public void testDescriptionIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailValidator.notEmptyString(null);
        });
        assertTrue(ItemDetailValidator.notEmptyString("Test Description"));
    }

    @Test
    @DisplayName("Shelf-life is an integer")
    public void testShelfLifeIsInteger() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailValidator.inputIsInteger("Test");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailValidator.inputIsInteger("15.5");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailValidator.inputIsInteger("true");
        });
        assertTrue(ItemDetailValidator.inputIsInteger("10000"));
    }

    @Test
    @DisplayName("Shelf life is greater than zero")
    public void testShelfLifeIsGreaterThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailValidator.greaterThanZero(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailValidator.greaterThanZero(-100);
        });
        assertTrue(ItemDetailValidator.greaterThanZero(1000));
    }

    @Test
    @DisplayName("Item Detail full validation")
    public void testItemDetail() {
        ItemDetail itemDetail = new ItemDetail("Name", "14342-4c", "This is a test description");
        assertTrue(ItemDetailValidator.validateItemDetails(itemDetail));
    }
}
