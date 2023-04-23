package com.petcove.inventoryservice.repository;

import com.petcove.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.Entity;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    List<Inventory> findBySkuCodeIn(List<String> skuCode);
    Optional<Inventory> findBySkuCode(String skuCode);
}
