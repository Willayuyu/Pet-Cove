package com.petcove.inventoryservice.controller;

import com.petcove.inventoryservice.dto.InventoryResponse;
import com.petcove.inventoryservice.service.InventoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    //multiple items (path variable): http://localhost:8082/api/inventory/racket1,racket1-red
    // request param: http://localhost:8082/api/inventory?skuCode=racket1&skuCode=racket1-red
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    //判断product是否in stock
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode, @RequestParam List<Integer> quantity){
        return inventoryService.isInstock(skuCode,quantity);

    }

}
