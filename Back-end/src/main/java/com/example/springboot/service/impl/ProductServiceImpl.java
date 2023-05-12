package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.OrderItem;
import com.example.springboot.entity.Product;
import com.example.springboot.mapper.OrderItemMapper;
import com.example.springboot.mapper.ProductMapper;
import com.example.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
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
     * findAllProductForSeller
     * @param sellerId
     * @return
     */
    @Override
    public List<Product> findAllProductForSeller(Integer sellerId) {
        List<Product> list1 = productMapper.findAllProductForSeller(sellerId);
        return list1;
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

    /**
     * deleteProductById
     * @param productId
     * @return
     */
    @Override
    public int deleteProductById(Integer productId) {
        return productMapper.deleteProductById(productId);
    }

    /**
     * updateProductById
     * @param product
     * @return
     */
    @Override
    public int updateProductById(Product product) {
        return productMapper.updateProductById(product);
    }


}
