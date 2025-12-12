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

// handles the creation of items, which are the linkage between lotNumbers, storageBins, and itemDetails
@Entity
public class Item {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "item_detail_id")
    private ItemDetail itemDetail;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private Set<LotNumber> lotNumbers;

    @ManyToOne
    @JoinColumn(name = "storage_bin_id")
    private StorageBin storageBin;

    @Column(name = "is_active")
    private Boolean isActive = true;

    public Item() {
    }

    public Item(ItemDetail itemDetail, StorageBin storageBin) {
        this.itemDetail = itemDetail;
        this.storageBin = storageBin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemDetail getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(ItemDetail itemDetail) {
        this.itemDetail = itemDetail;
    }

    public Set<LotNumber> getLotNumbers() {
        return lotNumbers;
    }

    public void setLotNumbers(Set<LotNumber> lotNumbers) {
        this.lotNumbers = lotNumbers;
    }

    public StorageBin getStorageBin() {
        return storageBin;
    }

    public void setStorageBin(StorageBin storageBin) {
        this.storageBin = storageBin;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((itemDetail == null) ? 0 : itemDetail.hashCode());
        result = prime * result + ((lotNumbers == null) ? 0 : lotNumbers.hashCode());
        result = prime * result + ((storageBin == null) ? 0 : storageBin.hashCode());
        result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
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
        Item other = (Item) obj;
        if (id != other.id)
            return false;
        if (itemDetail == null) {
            if (other.itemDetail != null)
                return false;
        } else if (!itemDetail.equals(other.itemDetail))
            return false;
        if (lotNumbers == null) {
            if (other.lotNumbers != null)
                return false;
        } else if (!lotNumbers.equals(other.lotNumbers))
            return false;
        if (storageBin == null) {
            if (other.storageBin != null)
                return false;
        } else if (!storageBin.equals(other.storageBin))
            return false;
        if (isActive == null) {
            if (other.isActive != null)
                return false;
        } else if (!isActive.equals(other.isActive))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", itemDetail=" + itemDetail + ", storageBin=" + storageBin + ", isActive=" + isActive
                + "]";
    }
}
