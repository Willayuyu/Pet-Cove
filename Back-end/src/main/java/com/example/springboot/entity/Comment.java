package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("comment")
public class Comment {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    @TableField("buyer_id")
    private int buyerId;

    @TableField("product_id")
    private int productId;

    @TableField("order_item_id")
    private int orderItemId;

    @TableField("content")
    private String content;

    @TableField("created_date")
    private String createdDate;


}
