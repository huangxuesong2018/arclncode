package com.gupaoedu.sso.controller;


import com.gupaoedu.user.IUserCoreService;
import com.gupaoedu.user.dto.UserLoginRequest;
import com.gupaoedu.user.dto.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {
    @Autowired
    IUserCoreService userCoreService;

    @PostMapping("/login")
    public ResponseEntity doLogin(String username, String password){
        UserLoginRequest request=new UserLoginRequest();
        request.setPassword(password);
        request.setUserName(username);
        UserLoginResponse response=userCoreService.login(request);
        return ResponseEntity.ok(response);
    }
}
