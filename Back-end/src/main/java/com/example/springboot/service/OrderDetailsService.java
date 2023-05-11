package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.CheckoutInfo;
import com.example.springboot.entity.OrderDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "orderDetailsService")
public interface OrderDetailsService extends IService<OrderDetails> {

    public int createOrderDetails(CheckoutInfo info);
//    int buyerId, int sellerId, String shippingAddress

    public List<OrderDetails> getOrderList(int userId, boolean isBuyer);

}
