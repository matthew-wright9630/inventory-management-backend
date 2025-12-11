package com.skillstorm.inventory_management_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.inventory_management_backend.models.ItemQuantityView;

@Repository
public interface ItemQuantityViewRepository extends JpaRepository<ItemQuantityView, Integer> {

    public ItemQuantityView findById(int id);
}
