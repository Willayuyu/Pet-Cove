package com.example.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /*@GetMapping("/addUser/{name,phone,card}")
    public void addUser(@PathVariable String name, BigInteger phone, BigInteger card){
        userService.addUser(name, phone, card);
    }*/

//    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
//    public User helloUser() {
//        User user = userService.getUserById(1);
//        System.out.println(user);
//        return user;
//    }

//    @GetMapping("/login/{name}/{code}")
//    public BigInteger login(@PathVariable String name, @PathVariable String code){
//
//
//    }


    @PostMapping("/Login")
    public int Login(@RequestParam String username, @RequestParam String password){
//        System.out.printf("%s %s%n", username, password);
        int state =  userService.login(username, password);
        System.out.println(state);
        return state;

    }

    @GetMapping("/GetProfile")
    public User GetProfile(@RequestParam String username){
        User user = userService.getUserByName(username);
        return user;
    }

    @PostMapping("/Register")
    public User Register(@RequestBody User user){
        userService.addUser(user);
        return user;
    }

    @PostMapping("/UpdateProfile")
    public int UpdateProfile(@RequestBody User user){
        int state = userService.updateProfile(user);
        return state;
    }


}