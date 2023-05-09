package com.example.springboot.mapper;

import com.example.springboot.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CartMapper {

    /**
     * addProductToCart
     * @param cart
     */
    void addProductToCart(Cart cart);


}
