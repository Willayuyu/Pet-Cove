package com.example.springboot.controller;

import com.example.springboot.entity.Product;
import com.example.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * find all product
     * @return list
     */
    @GetMapping("/findAllProduct")
    public List<Product> findAllProduct(){
        List<Product> list = productService.findAllProduct();
        return list;
    }

    /**
     * findProductByInput
     * @param productName
     * @param productCategories
     * @param productColor
     * @return
     */
    @GetMapping("/findProductByInput")
    public List<Product> findProductByInput(@RequestParam(required = false) String productName,
                                            @RequestParam(required = false) String productCategories,
                                            @RequestParam(required = false) String productColor){
        Map<String, Object> params = new HashMap<>();
        params.put("productName", productName);
        params.put("productCategories", productCategories);
        params.put("productColor", productColor);

        return productService.findProductByInput(params);
    }

    /**
     * insertProduct
     * @param product
     * @return
     */
    @PostMapping("/insertProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.insertProduct(product);
        return new ResponseEntity<>("Product added successfully.", HttpStatus.CREATED);
    }



}
