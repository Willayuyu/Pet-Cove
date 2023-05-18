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
     *
     * @param cart
     * @return
     */
    @Override
    public int addProductToCart(Cart cart){
        Cart cart1 = cartMapper.findProductInCart(cart.getUserId(),cart.getProductId());
        if (cart1 != null) {
            cart.setProductQuantity(cart1.getProductQuantity() + 1);
            cartMapper.updateCartItemQuantity(cart);
        } else {
            cart.setUserId(cart.getUserId());
            cart.setUserName(cart.getUserName());
            cart.setProductId(cart.getProductId());
            cart.setProductName(cart.getProductName());
            cart.setProductPrice(cart.getProductPrice());
            cart.setProductQuantity(cart.getProductQuantity());
            cart.setProductImage(cart.getProductImage());
            cartMapper.addProductToCart(cart);
        }

        return 0;
    }

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

    @Override
    public Cart findProductInCart(Integer userId, Integer productId) {
        return cartMapper.findProductInCart(userId, productId);
    }


}
