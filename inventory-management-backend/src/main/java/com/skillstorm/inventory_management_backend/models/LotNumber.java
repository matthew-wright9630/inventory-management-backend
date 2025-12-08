package com.skillstorm.inventory_management_backend.models;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
    private LocalDateTime dateAdded;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "lotNumber")
    @JsonIgnore
    private Set<Item> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LotNumber() {
    }

    public LotNumber(int id, LocalDateTime dateAdded, Boolean isActive) {
        this.id = id;
        this.dateAdded = dateAdded;
        this.isActive = isActive;
    }

    public LotNumber(LocalDateTime dateAdded, Boolean isActive) {
        this.dateAdded = dateAdded;
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((dateAdded == null) ? 0 : dateAdded.hashCode());
        result = prime * result + ((items == null) ? 0 : items.hashCode());
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
        if (items == null) {
            if (other.items != null)
                return false;
        } else if (!items.equals(other.items))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LotNumber [id=" + id + ", dateAdded=" + dateAdded + "]";
    }
}
