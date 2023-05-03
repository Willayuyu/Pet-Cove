package com.example.springboot.service.impl;

import com.example.springboot.entity.Product;
import com.example.springboot.mapper.ProductMapper;
import com.example.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Override
    public List<Product> findAllProduct() {
        List<Product> list = productMapper.findAllProduct();

        return list;
    }
}
