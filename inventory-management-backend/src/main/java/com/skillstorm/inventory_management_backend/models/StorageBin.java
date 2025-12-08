package com.skillstorm.inventory_management_backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class StorageBin {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @Column(name = "storage_location")
    private String storageLocation;

    @Column(name = "is_active", insertable = false)
    private Boolean isActive;

    public StorageBin() {
    }

    public StorageBin(int id, Warehouse warehouse, String storageLocation, boolean isActive) {
        this.id = id;
        this.warehouse = warehouse;
        this.storageLocation = storageLocation;
        this.isActive = isActive;
    }

    public StorageBin(Warehouse warehouse, String storageLocation, boolean isActive) {
        this.warehouse = warehouse;
        this.storageLocation = storageLocation;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((warehouse == null) ? 0 : warehouse.hashCode());
        result = prime * result + ((storageLocation == null) ? 0 : storageLocation.hashCode());
        result = prime * result + (isActive ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StorageBin other = (StorageBin) obj;
        if (id != other.id)
            return false;
        if (warehouse == null) {
            if (other.warehouse != null)
                return false;
        } else if (!warehouse.equals(other.warehouse))
            return false;
        if (storageLocation == null) {
            if (other.storageLocation != null)
                return false;
        } else if (!storageLocation.equals(other.storageLocation))
            return false;
        if (isActive != other.isActive)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "StorageBin [id=" + id + ", warehouse=" + warehouse + ", storageLocation=" + storageLocation
                + ", isActive=" + isActive + "]";
    }

}
