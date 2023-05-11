package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cart")
public class Cart {
    @TableId(value = "cart_id",type = IdType.AUTO)
    private Integer cartId;
    @TableField("user_id")
    private Integer userId;
    @TableField("user_name")
    private String userName;
    @TableField("product_id")
    private Integer productId;
    @TableField("product_Name")
    private String productName;
    @TableField("product_price")
    private Long productPrice;
    @TableField("product_quantity")
    private Integer productQuantity;
    @TableField("product_image")
    private String productImage;

    @TableField("total_price")
    private Long totalPrice;

}
