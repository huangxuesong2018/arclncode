package com.gupao.micro.service.spring.cloud.ds.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientZKDSClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientZKDSClientApplication.class,args);
    }
}
