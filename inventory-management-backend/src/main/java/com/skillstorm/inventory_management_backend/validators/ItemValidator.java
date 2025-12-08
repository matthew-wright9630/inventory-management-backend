package com.skillstorm.inventory_management_backend.validators;

import com.skillstorm.inventory_management_backend.models.Item;
import com.skillstorm.inventory_management_backend.models.ItemDetail;
import com.skillstorm.inventory_management_backend.models.LotNumber;
import com.skillstorm.inventory_management_backend.models.StorageBin;

public class ItemValidator {

    public static boolean validateItem(Item item) {
        return (storageBinIsNotEmpty(item.getStorageBin()) && lotNumberIsNotEmpty(item.getLotNumber())
                && itemDetailIsNotEmpty(item.getItemDetail()));
    }

    public static boolean storageBinIsNotEmpty(StorageBin storageBin) {
        try {
            int storageBinId = storageBin.getId();
            if (storageBinId > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean lotNumberIsNotEmpty(LotNumber lotNumber) {
        try {
            int lotNumberId = lotNumber.getId();
            if (lotNumberId > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean itemDetailIsNotEmpty(ItemDetail itemDetail) {
        try {
            int itemDetailId = itemDetail.getId();
            if (itemDetailId > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
