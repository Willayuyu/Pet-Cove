package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.CheckoutInfo;
import com.example.springboot.entity.OrderDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "orderDetailsService")
public interface OrderDetailsService extends IService<OrderDetails> {

    /**
     * @param info check out information
     * @return 1 means success
     */
    public int createOrderDetails(CheckoutInfo info);
//    int buyerId, int sellerId, String shippingAddress

    /**
     * @param userId user id
     * @param isBuyer if user is buyer
     * @return list of order details
     */
    public List<OrderDetails> getOrderList(int userId, boolean isBuyer);

}
