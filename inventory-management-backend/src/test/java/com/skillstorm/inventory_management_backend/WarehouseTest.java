package com.skillstorm.inventory_management_backend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WarehouseTest {

    /*
        For a warehouse to be created:
            Name needs to be added
                - Name must contain at least 3 characters
                - Name cannot be empty
            Location needs to be added
                - Location must include a country
                        - Location country name must contain at least 3 characters
                        - Name cannot be empty
                - Locaiton must include a city
                        - Location city name must contain at least 3 characters
                        - Name cannot be empty
                - Location must include an address
                - Location should include a state/region field
            Maximum capacity must be added
    */

    // Creation Tests
    @Test
    @DisplayName("Name has at least three characters")
    public void testNameHasThreeCharacters() {
        assertFalse(Warehouse.hasThreeCharacters(""));
        assertFalse(Warehouse.hasThreeCharacters("Hi"));
        assertTrue(Warehouse.notEmptyString("Warehouse 1"));
    }

    @Test
    @DisplayName("Name is not null") 
    public void testNameIsNotNull(){
        assertThrows(NullPointerException.class, () -> {
            Warehouse.notEmptyString(null);
        });
        assertTrue(Warehouse.notEmptyString("Warehouse 1"));
    }

    @Test
    @DisplayName("Country name has at least three characters")
    public void testCountryHasThreeCharacters() {
        assertFalse(Warehouse.hasThreeCharacters(""));
        assertFalse(Warehouse.hasThreeCharacters("US"));
        assertThrows(NullPointerException.class, () -> {
            Warehouse.hasThreeCharacters(null);
        });
        assertTrue(Warehouse.hasThreeCharacters("United States of America"));
    }
    
    @Test
    @DisplayName("Country is not null") 
    public void testCountryIsNotNull(){
        assertThrows(NullPointerException.class, () -> {
            Warehouse.notEmptyString(null);
        });
        assertTrue(Warehouse.notEmptyString("United Kingdom"));
    }

    @Test
    @DisplayName("City name has at least three characters")
    public void testCityHasThreeCharacters() {
        assertFalse(Warehouse.hasThreeCharacters(""));
        assertFalse(Warehouse.hasThreeCharacters("aa"));
        assertThrows(NullPointerException.class, () -> {
            Warehouse.hasThreeCharacters(null);
        });
        assertTrue(Warehouse.hasThreeCharacters("Woodburn"));
    }
    
    @Test
    @DisplayName("City is not null") 
    public void testCityIsNotNull(){
        assertThrows(NullPointerException.class, () -> {
            Warehouse.notEmptyString(null);
        });
        assertTrue(Warehouse.notEmptyString("Woodburn"));
    }
}
