package com.example.springboot.mapper;

import com.example.springboot.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ProductMapper {
    /**
     * find all product
     * @return
     */
    List<Product> findAllProduct();
    /**
     * findProductByInput
     */
    List<Product> findProductByInput(@Param("params") Map<String, Object> params);

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
}
