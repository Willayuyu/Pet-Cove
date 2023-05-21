package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.User;
import org.springframework.stereotype.Component;

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
     * @Params
     * @Return
     **/
    public boolean checkPassword(String name, String password);

    /**
     *register new user data
     **/
    public void addUser(User newUser);


    /**
     *login
     **/

    int login(String name, String password, boolean isSeller);

    /**
     *logout
     **/
    public void logout(long Id);

    //update
    public int updateProfile(User user);


}
