package com.example.springboot.service;

import com.example.springboot.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    List<Product> findAllProduct();
}
