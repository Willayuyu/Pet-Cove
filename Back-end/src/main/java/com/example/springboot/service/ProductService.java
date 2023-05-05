package com.example.springboot.service;

import com.example.springboot.entity.Product;
import com.example.springboot.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface ProductService {
    /**
     * find all product
     * @return
     */
    List<Product> findAllProduct();

    /**
     * findProductByInput
     * @param params
     * @return
     */
    List<Product> findProductByInput(Map<String, Object> params);

}
