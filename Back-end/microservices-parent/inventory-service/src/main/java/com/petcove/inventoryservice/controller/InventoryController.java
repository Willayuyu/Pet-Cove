package com.petcove.inventoryservice.controller;

import com.petcove.inventoryservice.service.InventoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    //判断product是否in stock
    public boolean isInStock(@PathVariable("sku-code") String skuCode){
        return inventoryService.isInstock(skuCode);

    }

}
