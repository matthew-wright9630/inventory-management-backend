package com.skillstorm.inventory_management_backend.validators;

import com.skillstorm.inventory_management_backend.models.Location;
import com.skillstorm.inventory_management_backend.models.Warehouse;

public class WarehouseValidator {

    public boolean validateWarehouse(Warehouse warehouse) {
        return (hasThreeCharacters(warehouse.getName()) && notEmptyString(warehouse.getName())
                && hasThreeCharacters(warehouse.getAddress()) && notEmptyString(warehouse.getAddress())
                && hasThreeCharacters(warehouse.getAddressLineTwo()) && notEmptyString(warehouse.getAddressLineTwo())
                && inputIsInteger("" + warehouse.getMaximumCapacity())
                && greaterThanZero(warehouse.getMaximumCapacity()) && locationIsNotEmpty(warehouse.getLocation())
                && hasThreeCharacters(warehouse.getAddress()) && notEmptyString(warehouse.getAddress()));
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

    public static boolean inputIsInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean greaterThanZero(int input) {
        return input > 0;
    }

    public static boolean locationIsNotEmpty(Location location) {
        try {
            return !location.isEmpty();

        } catch (Exception e) {
            throw e;
        }
    }
}
