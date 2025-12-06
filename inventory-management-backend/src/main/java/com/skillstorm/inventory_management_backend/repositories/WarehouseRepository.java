package com.skillstorm.inventory_management_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.inventory_management_backend.models.Warehouse;

import jakarta.transaction.Transactional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    // public List<Warehouse> findWarehousesBy

    @Query("update Warehouse l set l.isActive = :new_isActive where id = :warehouse_id")
    @Modifying
    @Transactional
    public int deleteWarehouse(@Param("warehouse_id") int id, @Param("new_isActive") boolean active);
}
