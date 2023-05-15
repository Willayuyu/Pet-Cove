package com.example.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.entity.CheckoutInfo;
import com.example.springboot.entity.OrderDetails;
import com.example.springboot.entity.User;
import com.example.springboot.service.OrderDetailsService;
import com.example.springboot.service.OrderItemService;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/order")
@RestController
public class OrderController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private UserService userService;

    @PostMapping("/Checkout")
    public int Checkout(@RequestBody CheckoutInfo checkoutInfo){
        System.out.println(checkoutInfo);
        int orderId = orderDetailsService.createOrderDetails(checkoutInfo);
        orderItemService.createOrderItem(checkoutInfo, orderId);
        return 1;
    }

    @GetMapping("/BuyerGetOrderList")
    public List<OrderDetails> BuyerGetOrderList(@RequestParam int buyerId){
        List<OrderDetails> orderList = orderDetailsService.getOrderList(buyerId, true);
        return orderList;
    }

    @GetMapping("/SellerGetOrderList")
    public List<OrderDetails> SellerGetOrderList(@RequestParam int sellerId){
        List<OrderDetails> orderList = orderDetailsService.getOrderList(sellerId, false);
        return orderList;
    }

    @GetMapping("/GetOneOrder")
    public JSONObject BuyerGetOneOrder(@RequestParam int orderId){
        JSONObject json = new JSONObject();

//        json.put("buyerName", "buyer");

//        OrderDetails orderDetails = orderDetailsService.getById(orderId);
//        json.put("orderDetails", orderDetails);
//        int buyerId = orderDetails.getBuyerId();
//        User buyer = userService.getUserById(buyerId);
//        json.put("buyerName", buyer.getUsername());
        List<JSONObject> items = orderItemService.getItemsFromOrder(orderId);
        json.put("products", items);
        return json;
    }


    @GetMapping("/BuyerCancelOrder")
    public int CancelOrder(@RequestParam int orderId){
        OrderDetails orderDetails = orderDetailsService.getById(orderId);
        orderDetails.setStatus("Cancelled");
        orderDetailsService.updateById(orderDetails);
        return 1;
    }

}
