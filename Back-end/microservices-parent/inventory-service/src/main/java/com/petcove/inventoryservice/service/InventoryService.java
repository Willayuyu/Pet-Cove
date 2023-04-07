package com.petcove.inventoryservice.service;

import com.petcove.inventoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    public boolean isInstock(String skuCode){
        //check if the *object is present inside the optional or not
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}

