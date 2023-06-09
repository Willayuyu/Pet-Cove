package com.example.springboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.CheckoutInfo;
import com.example.springboot.entity.OrderDetails;
import com.example.springboot.entity.OrderItem;
import com.example.springboot.entity.Product;
import com.example.springboot.mapper.OrderItemMapper;
import com.example.springboot.service.OrderItemService;
import com.example.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderItemImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductService productService;

    @Override
    public void createOrderItem(CheckoutInfo info, int orderId) {

        List<Integer> productIds = info.getProductIds();
        Map<Integer, Integer> productIdCounts = new HashMap<>();
        for (Integer id : productIds) {
            Integer count = productIdCounts.get(id);
            if (count == null) {
                count = 0;
            }
            productIdCounts.put(id, count + 1);
        }

        for (Integer id : productIdCounts.keySet()){

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(id);
            orderItem.setCount(productIdCounts.get(id));
            orderItemService.save(orderItem);

        }

    }

    @Override
    public List<JSONObject> getItemsFromOrder(int orderId) {
        /*
          [
          {name: xx, price: xx, count: xx, total: xx},
          {name: xx, price: xx, count: xx, total: xx},
          ]
          */
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        List<OrderItem> orderItems = orderItemService.list(queryWrapper);
        List<JSONObject> jsonList = new ArrayList<>();
        for (OrderItem item : orderItems){
            Product product = productService.getById(item.getProductId());
            JSONObject json = new JSONObject();
            json.put("orderItemId", item.getId());
            json.put("name", product.getProductName());
            json.put("price", product.getProductPrice());
            json.put("count", item.getCount());
            json.put("total", product.getProductPrice() * item.getCount());
            jsonList.add(json);


        }

        return jsonList;
    }
}
