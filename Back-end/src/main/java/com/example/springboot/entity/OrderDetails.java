package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("order_details")
public class OrderDetails {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    @TableField("buyer_id")
    private int buyerId;

    @TableField("seller_id")
    private int sellerId;

    @TableField("cost")
    private float cost;

//    @TableField("order_number")
//    private String orderNumber;

//    @TableField("payment_id")
//    private String paymentId;

//    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @TableField(value = "created_date")
    private String createdDate;

//    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @TableField(value = "created_time")
    private String createdTime;

    /**
     * "Pending Shipment"
     * "Shipping in Progress"
     * "Cancelled"
     * "Delivered"
     * */
    @TableField("status")
    private String status = "Pending Shipment";

    @TableField("shipping_address")
    private String shippingAddress;
}
