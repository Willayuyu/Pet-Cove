package com.example.springboot.mapper;

import com.example.springboot.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CartMapper {

    /**
     * addProductToCart
     * @param cart
     */
    void addProductToCart(Cart cart);

    /**
     * deleteCartItem
     * @param cart
     * @return
     */
    int deleteCartItem(Cart cart);

    /**
     * updateCartItemQuantity
     * @param cart
     * @return
     */
    int updateCartItemQuantity(Cart cart);

    /**
     * getAllCartItems
     * @param userId
     * @return
     */
    List<Cart> getAllCartItems(Integer userId);




}
