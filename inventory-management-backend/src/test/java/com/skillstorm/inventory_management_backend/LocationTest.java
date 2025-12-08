package com.skillstorm.inventory_management_backend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skillstorm.inventory_management_backend.validators.LocationValidator;
import com.skillstorm.inventory_management_backend.validators.LocationValidator;

public class LocationTest {

    /**
     * Location must include a country
     * - Location country name must contain at least 3 characters
     * - Country cannot be empty
     * Location should include a state/region field
     * - State/Region must contain at least 3 characters
     * - State/Region cannot be empty
     */

    @Test
    @DisplayName("Country name has at least three characters")
    public void testCountryHasThreeCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            LocationValidator.hasThreeCharacters("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            LocationValidator.hasThreeCharacters("US");
        });
        assertTrue(LocationValidator
                .hasThreeCharacters("United States of America"));
    }

    @Test
    @DisplayName("Country is not null")
    public void testCountryIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            LocationValidator.notEmptyString(null);
        });
        assertTrue(LocationValidator.notEmptyString("Canada"));
    }

    @Test
    @DisplayName("State or region has at least two characters")
    public void testStateOrRegionHasTwoCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            LocationValidator.hasTwoCharacters("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            LocationValidator.hasTwoCharacters("A");
        });
        assertTrue(LocationValidator.hasTwoCharacters("IN"));
        assertTrue(LocationValidator.hasTwoCharacters("Idiana"));
    }

    @Test
    @DisplayName("State or region is not Null")
    public void testStateOrRegionIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            LocationValidator.notEmptyString(null);
        });
        assertTrue(LocationValidator.notEmptyString("South Carolina"));
    }
}
