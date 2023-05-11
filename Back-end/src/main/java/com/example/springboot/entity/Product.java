package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@TableName("product")
public class Product implements Serializable {
    @TableId(value = "product_id",type = IdType.AUTO)
    private Integer productId;
    @TableField("product_name")
    private String productName;
    @TableField("product_description")
    private String productDescription;
    @TableField("product_price")
    private Float productPrice;
    @TableField("product_num")
    private Integer productNum;
    @TableField("product_image")
    private String productImage;
    @TableField("product_status")
    private Integer productStatus;

    @TableField("product_categories")
    private String productCategories;
    @TableField("product_color")
    private String productColor;

//    @TableField("minPrice")
//    private Long minPrice;
//    @TableField("maxPrice")
//    private Long maxPrice;


}

