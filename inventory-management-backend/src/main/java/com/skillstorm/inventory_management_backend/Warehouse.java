package com.skillstorm.inventory_management_backend;

public class CreateWarehouse {
    
    public static boolean hasThreeCharacters(String input) {
        try {
            if (input.length() > 2) {
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean hasLessThanFiftyCharacters(String input) {
        try {
            if (input.length() <= 50) {
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
