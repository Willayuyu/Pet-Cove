package com.petcove.inventoryservice.service;

import com.petcove.inventoryservice.dto.InventoryResponse;
import com.petcove.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
//@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    //@SneakyThrows
    public List<InventoryResponse> isInstock(List<String> skuCode){
        /*Simulate time-out exception
        log.info("Wait started");
        Thread.sleep(10000);
        log.info("wait ended");
        */
        //check if the *object git is present inside the optional or not
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()
                ).toList();
    }
}

