package com.example.springboot.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("order_items")
public class OrderItem {

    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    @TableId("order_id")
    private int orderId;

    @TableId("product_id")
    private int productId;

    @TableId("count")
    private int count;

//    @TableId("created_at")
//    private int createdDate;


}
