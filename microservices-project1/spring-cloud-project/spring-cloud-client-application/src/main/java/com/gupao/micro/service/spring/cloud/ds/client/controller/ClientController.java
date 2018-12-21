package com.gupao.micro.service.spring.cloud.ds.client.controller;

import com.gupao.micro.service.spring.cloud.ds.client.loadbalance.LoadBalanceRequestInterceptor;
import com.gupao.micro.service.spring.cloud.ds.client.loadbalance.MyCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Spring Cloud Zookeeper 做为注册中心，实现负载均衡
 */
@RestController
public class ClientController {
    @Autowired   //依赖注入自定义的RestTemplate Bean
    @MyCustomizer
    private RestTemplate restTemplate;

    @Autowired//依赖注入 Ribbon RestTemplate Bean
    @LoadBalanced
    private RestTemplate lbRestTemplate;

    @Value("${spring.application.name}")
    private String currentServiceName;


    @GetMapping("/invoke/{serviceName}/say")
    public String invokeSay(@PathVariable String serviceName,@RequestParam String message){
        return restTemplate.getForObject("/"+serviceName+"/say?message="+message,String.class);
    }

    @GetMapping("/lb/invoke/{serviceName}/say")
    public String lbInvokeSay(@PathVariable String serviceName,@RequestParam String message){
        return lbRestTemplate.getForObject("http://"+serviceName+"/say?message="+message,String.class);
    }
/*
    //第一个版本
    private volatile Set<String> targetUrls = new HashSet<>();
     @Scheduled(fixedDelay = 10*1000)//10秒钟更新一次
    public void updateTargetUrls(){
        //获取当前应用的机器列表
        //http://${host}:${port}
        Set<String> oldTargetUrls = this.targetUrls;
        *//*通过应用名称从discoveryClient中获得在zookeeper注册的节点
         在应用启动时，在zookeeper中创建 ${spring.application.name} 节点
         [zk: localhost:2181(CONNECTED) 1] ls /services
         [spring-cloud-client-application]
         *//*
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(currentServiceName);
        Set<String> newTargetUrls = serviceInstances
                .stream()
                .map(s -> s.isSecure() ?
                "https://"+s.getHost()+":"+s.getPort():
                "http://"+s.getHost()+":"+s.getPort()
                ).collect(Collectors.toSet());
        //swap
        this.targetUrls = newTargetUrls;
        System.out.println("服务节点列表-->"+this.targetUrls);
        //帮助GC回收
        oldTargetUrls.clear();
    }*/

/*    @GetMapping("/invoke/say")
    public String invokeSay(@RequestParam String message){
        //服务器列表快照
        List<String> targetUrls = new ArrayList<>(this.targetUrls);
        int size = targetUrls.size();
        int index = new Random().nextInt(size);
        //选择其中一台服务器
        String targetURL = targetUrls.get(index);
        return restTemplate.getForObject(targetURL+"/say?message="+message,String.class);
    }*/



    @GetMapping("say")
    public String say(@RequestParam String message){
        System.out.println("接收到消息-》"+message);
        return "Hello,"+message;

    }

    @Bean
    public ClientHttpRequestInterceptor interceptor(){
        return new LoadBalanceRequestInterceptor();
    }

    //Ribbon RestTemplate Bean
    @LoadBalanced
    @Bean
    public RestTemplate loadBalancedRestTemplate() {
        return new RestTemplate();
    }

    //自定义RestTemplate Bean
    @Bean
    @MyCustomizer
    public RestTemplate restTemplate(ClientHttpRequestInterceptor interceptor){
        RestTemplate restTemplate = new RestTemplate();
        //增加拦截器
       // restTemplate.setInterceptors(Arrays.asList(interceptor));
        return restTemplate;
    }

    @Bean
    public Object customizer(@MyCustomizer Collection<RestTemplate> restTemplates,
                             ClientHttpRequestInterceptor interceptor){
        restTemplates.forEach(r ->{
            r.setInterceptors(Arrays.asList(interceptor));
        });
        return  new Object();
    }
}
