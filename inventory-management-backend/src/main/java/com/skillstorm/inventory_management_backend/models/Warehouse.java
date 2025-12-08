package com.skillstorm.inventory_management_backend.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Warehouse {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column(name = "max_capacity")
    private int maximumCapacity;

    @Column
    private String address;

    @Column(name = "address_two")
    private String addressLineTwo;

    @Column(name = "is_active", insertable = false)
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "warehouse")
    @JsonIgnore
    private Set<StorageBin> storageBins;

    public Warehouse() {
    }

    public Warehouse(int id, String name, int maximumCapacity, String address, String addressLineTwo, boolean isActive,
            Location location, Set<StorageBin> storageBins) {
        this.id = id;
        this.name = name;
        this.maximumCapacity = maximumCapacity;
        this.address = address;
        this.addressLineTwo = addressLineTwo;
        this.isActive = isActive;
        this.location = location;
        this.storageBins = storageBins;
    }

    public Warehouse(String name, int maximumCapacity, String address, String addressLineTwo, boolean isActive,
            Location location, Set<StorageBin> storageBins) {
        this.name = name;
        this.maximumCapacity = maximumCapacity;
        this.address = address;
        this.addressLineTwo = addressLineTwo;
        this.isActive = isActive;
        this.location = location;
        this.storageBins = storageBins;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Set<StorageBin> getStorageBins() {
        return storageBins;
    }

    public void setStorageBins(Set<StorageBin> storageBins) {
        this.storageBins = storageBins;
    }

    public boolean isEmpty() {
        System.out.println(id);
        return (id <= 0);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + maximumCapacity;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((addressLineTwo == null) ? 0 : addressLineTwo.hashCode());
        result = prime * result + (isActive ? 1231 : 1237);
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((storageBins == null) ? 0 : storageBins.hashCode());
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
        Warehouse other = (Warehouse) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (maximumCapacity != other.maximumCapacity)
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (addressLineTwo == null) {
            if (other.addressLineTwo != null)
                return false;
        } else if (!addressLineTwo.equals(other.addressLineTwo))
            return false;
        if (isActive != other.isActive)
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (storageBins == null) {
            if (other.storageBins != null)
                return false;
        } else if (!storageBins.equals(other.storageBins))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Warehouse [id=" + id + ", name=" + name + ", maximumCapacity=" + maximumCapacity + ", address="
                + address + ", addressLineTwo=" + addressLineTwo + ", isActive=" + isActive + ", location=" + location
                + "]";
    }

}
