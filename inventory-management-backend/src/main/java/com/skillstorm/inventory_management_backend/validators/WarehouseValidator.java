package com.skillstorm.inventory_management_backend.validators;

import com.skillstorm.inventory_management_backend.models.Location;
import com.skillstorm.inventory_management_backend.models.Warehouse;

public class WarehouseValidator {

    public static boolean validateWarehouse(Warehouse warehouse) {
        if (warehouse.getAddressLineTwo() != "") {
            // if addressLineTwo optional field is added, it should be at least 3 characters
            // long
            return (notEmptyString(warehouse.getName()) && hasThreeCharacters(warehouse.getAddressLineTwo())
                    && hasThreeCharacters(warehouse.getName())
                    && notEmptyString(warehouse.getAddress())
                    && hasThreeCharacters(warehouse.getAddress())
                    && inputIsInteger("" + warehouse.getMaximumCapacity())
                    && greaterThanZero(warehouse.getMaximumCapacity()) && locationIsNotEmpty(warehouse.getLocation())
                    && hasThreeCharacters(warehouse.getAddress()));
        }
        // if addressLineTwo optional field is not added, the validation should be
        // omitted.
        return (notEmptyString(warehouse.getAddress()) && notEmptyString(warehouse.getName())
                && hasThreeCharacters(warehouse.getAddress()) && hasThreeCharacters(warehouse.getName())
                && inputIsInteger("" + warehouse.getMaximumCapacity())
                && greaterThanZero(warehouse.getMaximumCapacity()) && locationIsNotEmpty(warehouse.getLocation())
                && hasThreeCharacters(warehouse.getAddress()));
    }

    public static boolean hasThreeCharacters(String input) {
        if (input.length() < 3) {
            throw new IllegalArgumentException("Input: " + input + " cannot be less than 3 characters.");
        }
        return true;
    }

    public static boolean notEmptyString(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input string cannot be null.");
        }
        return true;
    }

    public static boolean inputIsInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("Input: " + input + " must be an integer");
        }
    }

    public static boolean greaterThanZero(int input) {
        if (input <= 0) {
            throw new IllegalArgumentException("Input: " + input + " must be larger than 0");
        }
        return true;
    }

    public static boolean locationIsNotEmpty(Location location) {
        try {
            int locationId = location.getId();
            if (locationId > 0) {
                return true;
            } else {
                return false;
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Location is required to add to a warehouse.");
        } catch (Exception e) {
            throw e;
        }
    }
}
