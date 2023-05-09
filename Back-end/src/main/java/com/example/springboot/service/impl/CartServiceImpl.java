package com.example.springboot.service.impl;

import com.example.springboot.entity.Cart;
import com.example.springboot.mapper.CartMapper;
import com.example.springboot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;

    /**
     * addProductToCart
     * @param cart
     */
    @Override
    public void addProductToCart(Cart cart){cartMapper.addProductToCart(cart);}

    /**
     * deleteCartItem
     * @param cart
     * @return
     */
    @Override
    public int deleteCartItem(Cart cart) {
        return cartMapper.deleteCartItem(cart);
    }

    /**
     * updateCartItemQuantity
     * @param cart
     * @return
     */
    @Override
    public int updateCartItemQuantity(Cart cart) {
        return cartMapper.updateCartItemQuantity(cart);
    }

    @Override
    public long calculateTotalPrice(List<Cart> cartItems) {
        long totalPrice = 0;
        for (Cart cartItem : cartItems) {
            long productPrice = cartItem.getProductPrice();
            int productQuantity = cartItem.getProductQuantity();
            totalPrice += productPrice * productQuantity;
        }
        return totalPrice;
    }

    @Override
    public List<Cart> getAllCartItems(Integer userId) {
        return cartMapper.getAllCartItems(userId);
    }


}
