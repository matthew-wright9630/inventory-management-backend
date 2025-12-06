package com.skillstorm.inventory_management_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.inventory_management_backend.models.Location;

import jakarta.transaction.Transactional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

        @Query("update Location l set l.isActive = :new_isActive where id = :location_id")
        @Modifying
        @Transactional
        public int deleteLocation(@Param("location_id") int id, @Param("new_isActive") boolean active);
}
