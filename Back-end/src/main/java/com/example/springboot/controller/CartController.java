package com.example.springboot.controller;

import com.example.springboot.entity.Cart;
import com.example.springboot.entity.Product;
import com.example.springboot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     * addProductToCart
     * @param cart
     * @return
     */
    @PostMapping("/addProductToCart")
    public ResponseEntity<String> addProductToCart(@RequestBody Cart cart) {
        cartService.addProductToCart(cart);
        return new ResponseEntity<>("Product added successfully.", HttpStatus.CREATED);
    }



}
