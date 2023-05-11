package com.example.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.entity.CheckoutInfo;
import com.example.springboot.entity.OrderDetails;
import com.example.springboot.service.OrderDetailsService;
import com.example.springboot.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/Checkout")
    public int Checkout(@RequestBody CheckoutInfo checkoutInfo){
        int orderId = orderDetailsService.createOrderDetails(checkoutInfo);
        orderItemService.createOrderItem(checkoutInfo, orderId);
        return 1;
    }

    @PostMapping("/BuyerGetOrderList")
    public List<OrderDetails> BuyerGetOrderList(@RequestParam int buyerId){
        List<OrderDetails> orderList = orderDetailsService.getOrderList(buyerId, true);
        return orderList;
    }

    @PostMapping("/SellerGetOrderList")
    public List<OrderDetails> SellerGetOrderList(@RequestParam int sellerId){
        List<OrderDetails> orderList = orderDetailsService.getOrderList(sellerId, false);
        return orderList;
    }

    @PostMapping("/BuyerGetOneOrder")
    public JSONObject BuyerGetOneOrder(@RequestParam int buyerId, @RequestParam int orderId){
        JSONObject json = new JSONObject();
        OrderDetails orderDetails = orderDetailsService.getById(orderId);
//        json.put("buyerName", "buyer");

        json.put("orderDetails", orderDetails);
        List<JSONObject> items = orderItemService.getItemsFromOrder(orderId);
        json.put("products", items);
        return json;
    }

//    @PostMapping("/SellerGetOneOrder")
//    public JSONObject SellerGetOneOrder(@RequestParam int sellerId, @RequestParam int orderId){
//        JSONObject json = new JSONObject();
//        OrderDetails orderDetails = new OrderDetails();
////        json.put("buyerName", "buyer");
//        json.put("orderDetails", orderDetails);
//        List<JSONObject> items = orderItemService.getItemsFromOrder(orderId);
//        json.put("products", items);
//        return json;
//    }

    @PostMapping("/BuyerCancelOrder")
    public int CancelOrder(@RequestParam int orderId){
        OrderDetails orderDetails = orderDetailsService.getById(orderId);
        orderDetails.setStatus("Cancelled");
        orderDetailsService.updateById(orderDetails);
        return 1;
    }

}
