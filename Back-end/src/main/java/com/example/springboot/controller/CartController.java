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
     * Users can add the product to their cart.
     * @param cart
     * @return
     */
    @PostMapping("/addProductToCart")
    public ResponseEntity<String> addProductToCart(@RequestBody Cart cart) {
        cartService.addProductToCart(cart);
        return new ResponseEntity<>("Product added successfully.", HttpStatus.CREATED);
    }

    /**
     * Users can delete the product in their cart.
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
     * Users can update the quantity of product in their cart.
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
     * It can help the user to calculate the total price of the products in their cart.
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
     * It can help the user to check all  products in their cart.
     * @param userId
     * @return
     */
    @GetMapping("/getAllCartItems")
    public List<Cart> getAllCartItems(@RequestParam("userId") Integer userId){
        List<Cart> list = cartService.getAllCartItems(userId);
        return list;
    }

    /**
     * findProductInCart
     * @param userId
     * @param productId
     * @return
     */
    @GetMapping("/findProductInCart")
    public Cart findProductInCart(@RequestParam("userId") Integer userId,
                                        @RequestParam("productId") Integer productId){
        Cart cart = cartService.findProductInCart(userId, productId);
        return cart;

    }


}
