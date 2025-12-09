package com.skillstorm.inventory_management_backend.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String country;

    @Column(name = "state_or_region")
    private String stateOrRegion;

    @Column(name = "is_active", insertable = false)
    private Boolean isActive = true;

    @OneToMany(targetEntity = Warehouse.class, mappedBy = "location")
    @JsonIgnore
    private Set<Warehouse> warehouses;

    public Location() {
    }

    public Location(String country, String stateOrRegion, boolean isActive) {
        this.country = country;
        this.stateOrRegion = stateOrRegion;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStateOrRegion() {
        return stateOrRegion;
    }

    public void setStateOrRegion(String stateOrRegion) {
        this.stateOrRegion = stateOrRegion;
    }

    public Set<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public Boolean getIsActive() {
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
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((stateOrRegion == null) ? 0 : stateOrRegion.hashCode());
        result = prime * result + (isActive ? 1231 : 1237);
        result = prime * result + ((warehouses == null) ? 0 : warehouses.hashCode());
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
        Location other = (Location) obj;
        if (id != other.id)
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (stateOrRegion == null) {
            if (other.stateOrRegion != null)
                return false;
        } else if (!stateOrRegion.equals(other.stateOrRegion))
            return false;
        if (isActive != other.isActive)
            return false;
        if (warehouses == null) {
            if (other.warehouses != null)
                return false;
        } else if (!warehouses.equals(other.warehouses))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Location [id=" + id + ", country=" + country + ", stateOrRegion=" + stateOrRegion + ", isActive="
                + isActive + "]";
    }

}
