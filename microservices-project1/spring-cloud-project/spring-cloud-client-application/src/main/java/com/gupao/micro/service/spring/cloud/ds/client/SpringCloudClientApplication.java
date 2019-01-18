package com.gupao.micro.service.spring.cloud.ds.client;

import com.gupao.micro.service.spring.cloud.ds.client.annotation.EnableRestClient;
import com.gupao.micro.service.spring.cloud.ds.client.service.feign.client.SayingService;
import com.gupao.micro.service.spring.cloud.ds.client.service.rest.client.SayingRestService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication//标准Spring Boot应用
@EnableDiscoveryClient//激活服务发现客户端
@EnableScheduling//激活调度
@EnableFeignClients(clients = SayingService.class)
@EnableRestClient(clients = SayingRestService.class)
public class SpringCloudClientApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringCloudClientApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
