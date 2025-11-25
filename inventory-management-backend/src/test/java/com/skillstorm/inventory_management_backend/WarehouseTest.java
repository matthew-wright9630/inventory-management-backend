package com.skillstorm.inventory_management_backend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateWarehouseTest {

    /*
        For a warehouse to be created:
            Name needs to be added
                - Name must contain at least 3 characters
                - Name cannot have more than 50 characters
            Location needs to be added
                - Location must include a country
                - Location must include an address
                - Locaiton must include a city
                - If the country in United States of America, location must include the city
                - If the country in United States of America, location must include the state
                - If the country is not the United States of America, an optional region field should be shown
            Maximum capacity must be added
    */

    @Test
    @DisplayName("Name has at least three characters")
    public void testNameHasThreeCharacters() {
        assertFalse(CreateWarehouse.hasThreeCharacters(""));
        assertFalse(CreateWarehouse.hasThreeCharacters("Hi"));
        assertThrows(NullPointerException.class, () -> {
            CreateWarehouse.hasThreeCharacters(null);
        });
        assertTrue(CreateWarehouse.hasThreeCharacters("Warehouse 1"));
    }

    @Test
    @DisplayName("Name has less than 50 characters")
    public void testNameHasLessThanFiftyCharacters() {
        assertTrue(CreateWarehouse.hasLessThanFiftyCharacters("I AM FIFTY CHARACTERS LONG WAHAHAHAHAHA!!!!!!!!!!!"));
        assertFalse(CreateWarehouse.hasLessThanFiftyCharacters("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
    }
}
