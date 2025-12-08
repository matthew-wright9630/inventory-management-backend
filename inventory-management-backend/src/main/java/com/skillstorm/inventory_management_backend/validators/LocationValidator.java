package com.skillstorm.inventory_management_backend.validators;

import com.skillstorm.inventory_management_backend.models.Location;

public class LocationValidator {

    public static boolean validateLocation(Location location) {
        return (hasThreeCharacters(location.getCountry()) && notEmptyString(location.getCountry()) &&
                hasTwoCharacters(location.getStateOrRegion()) && notEmptyString(location.getStateOrRegion()));
    }

    public static boolean hasThreeCharacters(String input) {
        if (input.length() < 3) {
            throw new IllegalArgumentException("Input string cannot be less than 3 characters.");
        }
        return true;
    }

    public static boolean notEmptyString(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input string cannot be null.");
        }
        return true;
    }

    public static boolean hasTwoCharacters(String input) {
        if (input.length() < 2) {
            throw new IllegalArgumentException("Input string cannot be less than 3 characters.");
        }
        return true;
    }
}
