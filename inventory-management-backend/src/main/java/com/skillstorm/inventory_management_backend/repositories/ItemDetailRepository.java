package com.skillstorm.inventory_management_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.inventory_management_backend.models.ItemDetail;

import jakarta.transaction.Transactional;

@Repository
public interface ItemDetailRepository extends JpaRepository<ItemDetail, Integer> {

    @Query("update ItemDetails iDetails set iDetails.isActive = :new_isActive where id = :item_deails_id")
    @Modifying
    @Transactional
    public int deleteItemDetails(@Param("item_details_id") int id, @Param("new_isActive") boolean active);
}
