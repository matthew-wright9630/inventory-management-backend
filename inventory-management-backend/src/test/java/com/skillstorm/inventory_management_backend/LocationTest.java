package com.skillstorm.inventory_management_backend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skillstorm.inventory_management_backend.validators.LocationValidator;

public class LocationTest {

    /**
     * Location must include a country
     * - Location country name must contain at least 3 characters
     * - Country cannot be empty
     * Location must include a city
     * - Location city name must contain at least 3 characters
     * - City cannot be empty
     * - Location should include a state/region field
     * - State/Region must contain at least 3 characters
     * - State/Region cannot be empty
     */

    @Test
    @DisplayName("Country name has at least three characters")
    public void testCountryHasThreeCharacters() {
        assertFalse(LocationValidator.hasThreeCharacters(""));
        assertFalse(LocationValidator.hasThreeCharacters("US"));
        assertThrows(NullPointerException.class, () -> {
            LocationValidator.hasThreeCharacters(null);
        });
        assertTrue(LocationValidator.hasThreeCharacters("United States of America"));
    }

    @Test
    @DisplayName("Country is not null")
    public void testCountryIsNotNull() {
        assertThrows(NullPointerException.class, () -> {
            LocationValidator.notEmptyString(null);
        });
        assertTrue(LocationValidator.notEmptyString("United Kingdom"));
    }

    @Test
    @DisplayName("City name has at least three characters")
    public void testCityHasThreeCharacters() {
        assertFalse(LocationValidator.hasThreeCharacters(""));
        assertFalse(LocationValidator.hasThreeCharacters("aa"));
        assertThrows(NullPointerException.class, () -> {
            LocationValidator.hasThreeCharacters(null);
        });
        assertTrue(LocationValidator.hasThreeCharacters("Woodburn"));
    }

    @Test
    @DisplayName("City is not null")
    public void testCityIsNotNull() {
        assertThrows(NullPointerException.class, () -> {
            LocationValidator.notEmptyString(null);
        });
        assertTrue(LocationValidator.notEmptyString("Greenville"));
    }

    @Test
    @DisplayName("State or region has at least two characters")
    public void testStateOrRegionHasThreeCharacters() {
        assertFalse(LocationValidator.hasTwoCharacters(""));
        assertFalse(LocationValidator.hasTwoCharacters("a"));
        assertThrows(NullPointerException.class, () -> {
            LocationValidator.hasTwoCharacters(null);
        });
        assertTrue(LocationValidator.hasTwoCharacters("IN"));
        assertTrue(LocationValidator.hasTwoCharacters("Idiana"));
    }

    @Test
    @DisplayName("State or region is not Null")
    public void testStateOrRegionIsNotNull() {
        assertThrows(NullPointerException.class, () -> {
            LocationValidator.notEmptyString(null);
        });
        assertTrue(LocationValidator.notEmptyString("South Carolina"));
    }
}
