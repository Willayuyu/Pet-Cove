package com.example.springboot.entity;

import lombok.Data;

import java.util.List;

@Data
public class CheckoutInfo {

    private int buyerId;

    private int sellerId;

    private List<Integer> productIds;

    private String shippingAddress;


}
