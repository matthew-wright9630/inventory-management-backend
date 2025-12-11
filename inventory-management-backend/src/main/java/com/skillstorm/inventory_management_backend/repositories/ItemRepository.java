package com.skillstorm.inventory_management_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.inventory_management_backend.models.Item;
import com.skillstorm.inventory_management_backend.models.StorageBin;

import jakarta.transaction.Transactional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findAllByStorageBin(StorageBin storageBin);

    @Query("update Item i set i.isActive = :new_isActive where id = :item_id")
    @Modifying
    @Transactional
    public int deleteItem(@Param("item_id") int id, @Param("new_isActive") boolean active);
}
