package com.skillstorm.inventory_management_backend.validators;

import com.skillstorm.inventory_management_backend.models.Location;

public class LocationValidator {

    public static boolean validateLocation(Location location) {
        return (hasThreeCharacters(location.getCountry()) && notEmptyString(location.getCountry()) &&
                hasTwoCharacters(location.getStateOrRegion()) && notEmptyString(location.getStateOrRegion()));
    }

    public static boolean hasThreeCharacters(String input) {
        return input.length() >= 3;
    }

    public static boolean notEmptyString(String input) {
        try {
            return !input.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean hasTwoCharacters(String input) {
        return input.length() >= 2;
    }
}
