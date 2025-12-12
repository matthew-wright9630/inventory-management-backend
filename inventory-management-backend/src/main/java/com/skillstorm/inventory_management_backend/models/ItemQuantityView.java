package com.skillstorm.inventory_management_backend.models;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// View to help find the quantity of all items.
@Entity
@Immutable
@Table(name = "item_quantity_vw")
public class ItemQuantityView {

    @Id
    @Column(name = "item_detail_id")
    private int id;

    @Column
    private Integer quantity;

    public ItemQuantityView() {
    }

    public ItemQuantityView(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity != null ? quantity : 0;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
        ItemQuantityView other = (ItemQuantityView) obj;
        if (id != other.id)
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ItemQuantityView [id=" + id + ", quantity=" + quantity + "]";
    }
}
