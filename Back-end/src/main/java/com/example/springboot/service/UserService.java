package com.example.springboot.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.User;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component (value = "userService")
public interface UserService extends IService<User> {

    /**
     *get user data by id
     **/
    public User getUserById(long id);

    /**
     *get user data by name
     **/
    public User getUserByName(String username);

    /**
     *check password
     **/
    public boolean checkPassword(String name, String password);

    /**
     *add user data
     **/
    public void addUser(JSONObject userJson);


    /**
     *login
     **/

    int login(String name, String password);

    /**
     *logout
     **/
    public void logout(long Id);

}
