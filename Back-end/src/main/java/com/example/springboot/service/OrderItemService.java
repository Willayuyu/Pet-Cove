package com.example.springboot.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.CheckoutInfo;
import com.example.springboot.entity.OrderDetails;
import com.example.springboot.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "orderItemService")
public interface OrderItemService extends IService<OrderItem> {

    public void createOrderItem(CheckoutInfo info, int orderId);
//    int orderId, List<Integer> productIds

    /**
    * [
     * {name: xx, price: xx, count: xx, total: xx},
     * {name: xx, price: xx, count: xx, total: xx},
     * ]
    * */
    public List<JSONObject> getItemsFromOrder(int orderId);
}
