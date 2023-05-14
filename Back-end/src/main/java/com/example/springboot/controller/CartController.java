package com.example.springboot.controller;

import com.example.springboot.entity.Cart;
import com.example.springboot.entity.Product;
import com.example.springboot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * deleteCartItem
     * @param userId
     * @param productId
     * @return
     */
    @DeleteMapping("/deleteCartItem")
    public ResponseEntity<String> deleteCartItem(@RequestParam("userId") Integer userId,
                                                 @RequestParam("productId") Integer productId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setProductId(productId);

        int rowsAffected = cartService.deleteCartItem(cart);
        if (rowsAffected > 0) {
            return ResponseEntity.ok("Item removed from cart successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to remove item from cart.");
        }
    }


    /**
     * updateCartItemQuantity
     * @param userId
     * @param productId
     * @param productQuantity
     * @return
     */
    @PutMapping("/updateCartItemQuantity")
    public ResponseEntity<String> updateCartItemQuantity(@RequestParam("userId") Integer userId,
                                                         @RequestParam("productId") Integer productId,
                                                         @RequestParam("productQuantity") Integer productQuantity) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setProductId(productId);
        cart.setProductQuantity(productQuantity);
        int rowsAffected = cartService.updateCartItemQuantity(cart);
        if (rowsAffected > 0) {
            return ResponseEntity.ok("Item quantity updated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to update item quantity.");
        }
    }


    /**
     * calculateTotalPrice
     * @param userId
     * @return
     */
    @GetMapping("/totalPrice")
    public ResponseEntity<Long> getCartTotalPrice(@RequestParam("userId") Integer userId) {
        List<Cart> cartItems = cartService.getAllCartItems(userId);
        long totalPrice = cartService.calculateTotalPrice(cartItems);
        return ResponseEntity.ok(totalPrice);
    }

    /**
     * getAllCartItems
     * @param userId
     * @return
     */
    @GetMapping("/getAllCartItems")
    public List<Cart> getAllCartItems(@RequestParam("userId") Integer userId){
        List<Cart> list = cartService.getAllCartItems(userId);
        return list;
    }



}
