package com.skillstorm.inventory_management_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.inventory_management_backend.models.LotNumber;

import jakarta.transaction.Transactional;

@Repository
public interface LotNumberRepository extends JpaRepository<LotNumber, Integer> {

    @Query("update LotNumber ln set ln.isActive = :new_isActive where id = :lot_number_id")
    @Modifying
    @Transactional
    public int deleteLotNumber(@Param("lot_number_id") int id, @Param("new_isActive") boolean active);
}
