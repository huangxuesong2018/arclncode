package com.gupaoedu.sso.controller;


import com.gupaoedu.user.IUserCoreService;
import com.gupaoedu.user.dto.UserLoginRequest;
import com.gupaoedu.user.dto.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController extends BaseController {
    @Autowired
    IUserCoreService userCoreService;

    @PostMapping("/login")
    @Anoymous
    public UserLoginResponse doLogin(String username, String password,HttpServletResponse response){
        UserLoginRequest request=new UserLoginRequest();
        request.setPassword(password);
        request.setUserName(username);
        UserLoginResponse userLoginResponse =userCoreService.login(request);
        response.addHeader("Set-Cookie",
                "access_token="+userLoginResponse.getToken()+";Path=/;HttpOnly");
        return userLoginResponse;
    }

    @GetMapping("/test")
    public String test(HttpServletRequest request){
        return "success"+getUid();
    }
}
