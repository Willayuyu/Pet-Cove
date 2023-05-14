package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigInteger;

@Data
@TableName("user")
public class User {

    @TableId(value = "user_id",type = IdType.AUTO)
    private int userId;

    @TableField("user_name")
    private String username;

    @TableField("user_firstName")
    private String firstName;

    @TableField("user_lastName")
    private String lastName;

    @TableField("user_pswd")
    private String password;

    @TableField("user_email")
    private String email;

    @TableField("user_phone")
    private String phone;

    @TableField("user_address")
    private String address;

    /**
     * Buyer: 0, Seller: 1
     * **/
    @TableField("user_flag")
    private Integer flag;
}
