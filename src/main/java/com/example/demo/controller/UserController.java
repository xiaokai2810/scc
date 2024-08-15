package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/selectUserByid", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ResponseBody
    public String GetUser(User user){
        return userService.Sel(user).toString();
    }

    @RequestMapping(value = "/add", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String Add(User user){
           return userService.Add(user);
    }

//    @RequestMapping(value = "/update", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
//    public String Update(User user){
//        return userService.Update(user);
//    }
//
//    @RequestMapping(value = "/delete", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
//    public String Delete(User user){
//        return userService.Delete(user);
//    }
//
//    @GetMapping(value="/addgroup")
//    public String addgroup(User user)
//    {
//        return userService.addgroup(user);
//    }


}