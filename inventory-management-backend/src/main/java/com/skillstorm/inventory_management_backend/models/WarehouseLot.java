package com.skillstorm.inventory_management_backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// Junction table between warehouse and lotNumbers. Indicates which warehouse holds which lot.
@Entity
@Table(name = "warehouse_lots")
public class WarehouseLot {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private LotNumber lotNumber;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public WarehouseLot() {
    }

    public WarehouseLot(LotNumber lotNumber, Warehouse warehouse) {
        this.lotNumber = lotNumber;
        this.warehouse = warehouse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LotNumber getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(LotNumber lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
        result = prime * result + ((lotNumber == null) ? 0 : lotNumber.hashCode());
        result = prime * result + ((warehouse == null) ? 0 : warehouse.hashCode());
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
        WarehouseLot other = (WarehouseLot) obj;
        if (id != other.id)
            return false;
        if (isActive == null) {
            if (other.isActive != null)
                return false;
        } else if (!isActive.equals(other.isActive))
            return false;
        if (lotNumber == null) {
            if (other.lotNumber != null)
                return false;
        } else if (!lotNumber.equals(other.lotNumber))
            return false;
        if (warehouse == null) {
            if (other.warehouse != null)
                return false;
        } else if (!warehouse.equals(other.warehouse))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "WarehouseLot [id=" + id + ", isActive=" + isActive + ", lotNumber=" + lotNumber + ", warehouse="
                + warehouse + "]";
    }

}
