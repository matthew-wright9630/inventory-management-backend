package com.skillstorm.inventory_management_backend.validators;

public class WarehouseValidator {

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
}
