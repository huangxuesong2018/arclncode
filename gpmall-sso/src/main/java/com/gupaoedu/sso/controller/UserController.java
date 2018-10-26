package com.gupaoedu.sso.controller;


import com.arclncode.common.annotation.Anoymous;
import com.gupaoedu.sso.controller.supper.ResponseData;
import com.gupaoedu.sso.controller.supper.ResponseEnum;
import com.gupaoedu.user.IUserCoreService;
import com.gupaoedu.user.dto.UserLoginRequest;
import com.gupaoedu.user.dto.UserLoginResponse;
import com.gupaoedu.user.dto.UserRegisterRequest;
import com.gupaoedu.user.dto.UserRegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController extends BaseController {
    @Autowired
    IUserCoreService userCoreService;
    @Autowired
    KafkaTemplate kafkaTemplate;

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


    @GetMapping("/register")
    @Anoymous
    public @ResponseBody
    ResponseData register(String username, String password, String mobile){
        ResponseData data=new ResponseData();

        UserRegisterRequest request=new UserRegisterRequest();
        request.setMobile(mobile);
        request.setUsername(username);
        request.setPassword(password);
        try {
            UserRegisterResponse response = userCoreService.register(request);
            //异步化解耦
            kafkaTemplate.send("test",response.getUid());
            data.setMessage(response.getMsg());
            data.setCode(response.getCode());
        }catch(Exception e) {
            data.setMessage(ResponseEnum.FAILED.getMsg());
            data.setCode(ResponseEnum.FAILED.getCode());
        }
        return data;
    }

    @Anoymous
    @GetMapping("/test")
    public String test(HttpServletRequest request){
        kafkaTemplate.send("test","hello mic");
        return "success";
    }
}
