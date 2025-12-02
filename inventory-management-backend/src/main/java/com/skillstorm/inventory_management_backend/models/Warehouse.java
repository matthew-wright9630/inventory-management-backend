package com.skillstorm.inventory_management_backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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

    @ManyToOne
    @JoinColumn(name= "location_id")
    private Location location;
    
    public Warehouse() {
    }

    public Warehouse(int id, String name, int maximumCapacity, String address, String addressLineTwo, Location location) {
        this.id = id;
        this.name = name;
        this.maximumCapacity = maximumCapacity;
        this.address = address;
        this.addressLineTwo = addressLineTwo;
        this.location = location;
        }

    public Warehouse(String name, int maximumCapacity, String address, String addressLineTwo, Location location) {
        this.name = name;
        this.maximumCapacity = maximumCapacity;
        this.address = address;
        this.addressLineTwo = addressLineTwo;
        this.location = location;
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


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + maximumCapacity;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((addressLineTwo == null) ? 0 : addressLineTwo.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        return result;
    }


    @Override
    public String toString() {
        return "Warehouse [id=" + id + ", name=" + name + ", maximumCapacity=" + maximumCapacity + ", address="
                + address + ", addressLineTwo=" + addressLineTwo + ", location=" + location + "]";
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
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        return true;
    }





    public static boolean hasThreeCharacters(String input) {
            return input.length() >= 3;
    }

    public static boolean notEmptyString(String input) {
        try {
            return !input.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean hasTwoCharacters(String input) {
        return input.length() >= 2;
    }

    public static boolean inputIsInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean greaterThanZero(int input) {
        return input > 0;
    }
}
