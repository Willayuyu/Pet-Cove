package com.example.springboot.service;

import com.example.springboot.entity.Cart;

import java.util.List;

public interface CartService {

    /**
     * addProductToCart
     *
     * @param cart
     * @return
     */
    int addProductToCart(Cart cart);

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
     * calculateTotalPrice
     * @param cartItems
     * @return
     */
    long calculateTotalPrice(List<Cart> cartItems);

    List<Cart> getAllCartItems(Integer userId);

    Cart findProductInCart(Integer userId, Integer productId);



}
