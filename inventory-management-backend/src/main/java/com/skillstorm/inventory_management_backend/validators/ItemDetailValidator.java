package com.skillstorm.inventory_management_backend.validators;

import com.skillstorm.inventory_management_backend.models.ItemDetail;

public class ItemDetailValidator {

    public static boolean validateItemDetails(ItemDetail itemDetails) {
        return (hasThreeCharacters(itemDetails.getName()) && notEmptyString(itemDetails.getName()) &&
                hasThreeCharacters(itemDetails.getDescription()) && notEmptyString(itemDetails.getDescription()) &&
                notEmptyString(itemDetails.getSku()) && inputIsInteger("" + itemDetails.getShelfLife())
                && greaterThanZero(itemDetails.getShelfLife()));
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

    public static boolean inputIsInteger(String input) {
        if (input == "") {
            return true;
        }
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input must be a valid integer: " + input);
        }
    }

    public static boolean greaterThanZero(int input) {
        if (input <= 0) {
            throw new IllegalArgumentException("Input must be larger than 0: " + input);
        }
        return true;
    }
}
