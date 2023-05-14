package com.example.springboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private UserService userService;

    @Override
    public User getUserById(long id){
        return baseMapper.selectById(id);
    }

    @Override
    public User getUserByName(String name){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", name);
        List<User> userList = userService.list(queryWrapper);
        if(userList.size()>0)
            return userList.get(0);
        else return null;
    }

    @Override
    public boolean checkPassword(String name, String password){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", name);
        User user = userService.getOne(queryWrapper);
        return user.getPassword().equals(password);

    }

    @Override
    public void addUser(User newUser){
//        User user = new User();
//        user.setUsername(userJson.getString("name"));
//        user.setPassword(userJson.getString("password"));
//        user.setPhone(userJson.getString("phone"));
//        user.setEmail(userJson.getString("email"));
//        user.setAddress(userJson.getString("address"));
//        user.setFlag(userJson.getInteger("flag"));
        userService.save(newUser);
    }




    @Override
    public int login(String name, String password, boolean isSeller){

        User user = userService.getUserByName(name);

        // if isSeller is the same as flag

        if(user == null) {
            return -1;
        }

        if ((user.getFlag() == 1) ==isSeller){
            boolean pass = userService.checkPassword(name, password);
            return pass ? user.getUserId() : 0;
        }
        else {
            return -1;
        }


    }

    @Override
    public void logout(long Id) {

    }

    @Override
    public int updateProfile(User user) {
        User oldUser = userService.getUserByName(user.getUsername());
        int id = oldUser.getUserId();
        user.setUserId(id);
        userService.updateById(user);
        return 1;
    }


}
