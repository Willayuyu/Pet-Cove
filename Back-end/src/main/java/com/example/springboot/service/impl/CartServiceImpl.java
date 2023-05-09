package com.example.springboot.service.impl;

import com.example.springboot.entity.Cart;
import com.example.springboot.mapper.CartMapper;
import com.example.springboot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;

    /**
     * ddProductToCart
     * @param cart
     */
    @Override
    public void addProductToCart(Cart cart){cartMapper.addProductToCart(cart);}
}
