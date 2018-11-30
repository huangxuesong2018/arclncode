package com.gupao.micro.service.spring.cloud.ds.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@RestController
public class ClientController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String currentServiceName;

    @Autowired
    private DiscoveryClient discoveryClient;

    private volatile Set<String> targetUrls = new HashSet<>();

    @Scheduled(fixedDelay = 10*1000)//10秒钟更新一次
    public void updateTargetUrls(){
        //获取当前应用的机器列表
        //http://${host}:${port}
        Set<String> oldTargetUrls = this.targetUrls;
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(currentServiceName);
        Set<String> newTargetUrls = serviceInstances
                .stream()
                .map(s -> s.isSecure() ?
                "https://"+s.getHost()+":"+s.getPort():
                "http://"+s.getHost()+":"+s.getPort()
                ).collect(Collectors.toSet());
        //swap
        this.targetUrls = newTargetUrls;
        //帮助GC回收
        oldTargetUrls.clear();
    }

    @GetMapping("/invoke/say")
    public String invokeSay(@RequestParam String message){
        //服务器列表快照
        List<String> targetUrls = new ArrayList<>(this.targetUrls);
        int size = targetUrls.size();
        int index = new Random().nextInt(size);
        //选择其中一台服务器
        String targetURL = targetUrls.get(index);
        return restTemplate.getForObject(targetURL+"/say?message="+message,String.class);
    }



    @GetMapping("say")
    public String say(@RequestParam String message){
        System.out.println("接收到消息-》"+message);
        return "Hello,"+message;

    }

    //定义RestTemplate Bean
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
