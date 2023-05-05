package com.example.springboot.service.impl;

import com.example.springboot.entity.Product;
import com.example.springboot.mapper.ProductMapper;
import com.example.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    /**
     * findAllProduct
     * @return
     */
    @Override
    public List<Product> findAllProduct() {
        List<Product> list = productMapper.findAllProduct();
        return list;
    }

    /**
     * findProductByInput
     * @param params
     * @return
     */
    @Override
    public List<Product> findProductByInput(Map<String, Object> params){
        return productMapper.findProductByInput(params);
    }

    /**
     * insertProduct
     * @param product
     */
    @Override
    public void insertProduct(Product product) {
        productMapper.insertProduct(product);
    }


}
