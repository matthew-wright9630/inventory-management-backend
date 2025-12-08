package com.skillstorm.inventory_management_backend.validators;

import java.time.LocalDate;

import com.skillstorm.inventory_management_backend.models.Item;

public class LotNumberValidator {

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

    public static boolean verifyDateFormat(String input) {
        try {
            LocalDate.parse(input);
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Expected yyyy-MM-dd but got: " + input);
        }
    }

    public static boolean itemIsNotEmpty(Item item) {
        try {
            int itemId = item.getId();
            if (itemId > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
