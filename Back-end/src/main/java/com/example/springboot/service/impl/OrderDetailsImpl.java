package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.CheckoutInfo;
import com.example.springboot.entity.OrderDetails;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.OrderDetailsMapper;
import com.example.springboot.service.OrderDetailsService;
import com.example.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.sql.Date;
//import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderDetailsImpl extends ServiceImpl<OrderDetailsMapper, OrderDetails> implements OrderDetailsService {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private ProductService productService;

    @Override
    public int createOrderDetails(CheckoutInfo info) {

        int buyerId = info.getBuyerId();
        int sellerId = info.getSellerId();
        String shippingAddress = info.getShippingAddress();

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setBuyerId(buyerId);
        orderDetails.setSellerId(sellerId);
        orderDetails.setShippingAddress(shippingAddress);
//        orderDetails.setStatus("Pending Shipment");

        // calculate cost
        List<Integer> productIds = info.getProductIds();

        float cost = 0;
        for(int i = 0; i < productIds.size(); i++){
            float price = productService.getById(productIds.get(i)).getProductPrice();
            cost += price;
        }


        orderDetails.setCost(cost);

        // record date and time
        Date date = new Date();
//        Date sqlDate = new java.sql.Date(date.getTime());
//        Time sqlTime = new java.sql.Time(date.getTime());
//        orderDetails.setCreatedDate(sqlDate);
//        orderDetails.setCreatedTime(sqlTime);
        orderDetails.setCreatedDate(date);
        orderDetails.setCreatedTime(date);
        orderDetailsService.save(orderDetails);

        return orderDetails.getId();

    }

    @Override
    public List<OrderDetails> getOrderList(int userId, boolean isBuyer){

        QueryWrapper<OrderDetails> queryWrapper = new QueryWrapper<>();
        if (isBuyer){
            queryWrapper.eq("buyer_id", userId);
        }else {
            queryWrapper.eq("seller_id", userId);
        }
        List<OrderDetails> orderList = orderDetailsService.list(queryWrapper);

//        DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
//        for (OrderDetails orderDetails : orderList) {
//            Date createdDate = orderDetails.getCreatedDate();
//            Date formattedDate = null;
//            try {
//                formattedDate = outputFormat.parse(createdDate.toString());
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//            orderDetails.setCreatedDate(formattedDate);
//        }
        return orderList;
    }
}
