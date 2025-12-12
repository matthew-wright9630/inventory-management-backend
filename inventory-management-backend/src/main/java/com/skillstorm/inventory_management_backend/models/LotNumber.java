package com.skillstorm.inventory_management_backend.models;

import java.time.LocalDate;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// Contains the quantity information for items, as the same item can be placed on different lots.
@Entity
@Table(name = "lot")
public class LotNumber {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_added", updatable = false)
    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate dateAdded;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column
    private int quantity;

    @Column(name = "manufacture_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate manufactureDate;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToMany(mappedBy = "lotNumber")
    @JsonIgnore
    private Set<WarehouseLot> warehouseLots;

    public LotNumber() {
    }

    public LotNumber(int quantity, LocalDate manufactureDate, Item item) {
        this.quantity = quantity;
        this.manufactureDate = manufactureDate;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Set<WarehouseLot> getWarehouseLots() {
        return warehouseLots;
    }

    public void setWarehouseLots(Set<WarehouseLot> warehouseLots) {
        this.warehouseLots = warehouseLots;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((dateAdded == null) ? 0 : dateAdded.hashCode());
        result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
        result = prime * result + quantity;
        result = prime * result + ((manufactureDate == null) ? 0 : manufactureDate.hashCode());
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((warehouseLots == null) ? 0 : warehouseLots.hashCode());
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
        LotNumber other = (LotNumber) obj;
        if (id != other.id)
            return false;
        if (dateAdded == null) {
            if (other.dateAdded != null)
                return false;
        } else if (!dateAdded.equals(other.dateAdded))
            return false;
        if (isActive == null) {
            if (other.isActive != null)
                return false;
        } else if (!isActive.equals(other.isActive))
            return false;
        if (quantity != other.quantity)
            return false;
        if (manufactureDate == null) {
            if (other.manufactureDate != null)
                return false;
        } else if (!manufactureDate.equals(other.manufactureDate))
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (warehouseLots == null) {
            if (other.warehouseLots != null)
                return false;
        } else if (!warehouseLots.equals(other.warehouseLots))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LotNumber [id=" + id + ", dateAdded=" + dateAdded + ", isActive=" + isActive + ", quantity=" + quantity
                + ", manufactureDate=" + manufactureDate + ", item=" + item + "]";
    }
}
