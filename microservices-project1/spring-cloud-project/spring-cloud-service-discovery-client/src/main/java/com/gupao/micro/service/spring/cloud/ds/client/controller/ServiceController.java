package com.gupao.micro.service.spring.cloud.ds.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ServiceController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service")
    public List<String> getAllService(){
        return discoveryClient.getServices();
    }

    @RequestMapping("service/instances/${serviceName}")
    public List<String> getAllServiceInstance(@PathVariable String serviceName){
        return discoveryClient.getInstances(serviceName)
                .stream()
                .map(s -> s.getServiceId() + "-"+s.getHost()+":"+s.getPort())
                .collect(Collectors.toList());
    }
}
