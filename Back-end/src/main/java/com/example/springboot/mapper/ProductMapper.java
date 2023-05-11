package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ProductMapper extends BaseMapper<Product> {
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

    /**
     * updateProductById
     * @param product
     * @return
     */
    @Update("UPDATE product " +
            "SET product_name = #{productName}, product_description = #{productDescription}, product_price = #{productPrice}, product_num = #{productNum}, product_image = #{productImage}, product_status = #{productStatus}, product_categories = #{productCategories}, product_color = #{productColor} " +
            "WHERE product_id = #{productId}")
    int updateProductById(Product product);
}
