package com.gupao.user.web.controller;

import com.gupao.domain.service.UserService;
import com.gupao.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestApiController {
    @Autowired
    private UserService userService;
    @PostMapping("/user/save")
    public User saveUser(@RequestParam String name){
        User user = new User();
        user.setName(name);
        if(userService.save(user)){
            return user;
        }else {
            return null;
        }
    }
}
