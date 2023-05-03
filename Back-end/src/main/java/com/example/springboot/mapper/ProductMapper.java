package com.example.springboot.mapper;

import com.example.springboot.entity.Product;

import java.util.List;

public interface ProductMapper {
    List<Product> findAllProduct();
}
