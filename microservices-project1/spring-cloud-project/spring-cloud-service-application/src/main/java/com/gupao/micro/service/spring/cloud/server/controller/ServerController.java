package com.gupao.micro.service.spring.cloud.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    //取到应用的名称
    @Value("${spring-cloud-server-application}")
    private String currentServiceName;

    @RequestMapping("/say")
    public String say(@RequestParam String message){
        System.out.println("ServerController接收到消息-》"+message);
        return "Hello,"+message;
    }
}
