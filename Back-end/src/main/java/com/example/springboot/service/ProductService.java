package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Product;
import com.example.springboot.mapper.ProductMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Component(value = "productService")
public interface ProductService extends IService<Product> {
    /**
     * find all product
     * @return
     */
    List<Product> findAllProduct();

    /**
     * findAllProductForSeller
     * @param sellerId
     * @return
     */
    List<Product> findAllProductForSeller(Integer sellerId);

    /**
     * findProductDetails
     * @param productId
     * @return
     */
    List<Product> findProductDetails(Integer productId);

    /**
     * findProductByInput
     * @param params
     * @return
     */
    List<Product> findProductByInput(Map<String, Object> params);

    /**
     * insertProduct
     * @param product
     */
    void insertProduct(Product product);

    /**
     * deleteProductById
     * @param productId
     * @return
     */
    int deleteProductById(Integer productId);

    /**
     * updateProductById
     * @param product
     * @return
     */
    int updateProductById(Product product);




}
