package com.gupao.micro.service.spring.cloud.ds.client;

import com.gupao.micro.service.spring.cloud.client.event.HttpRemoteAppEventListener;
import com.gupao.micro.service.spring.cloud.ds.client.annotation.EnableRestClient;
import com.gupao.micro.service.spring.cloud.ds.client.service.feign.client.SayingService;
import com.gupao.micro.service.spring.cloud.ds.client.service.rest.client.SayingRestService;
import com.gupao.micro.service.spring.cloud.ds.client.stream.SimpleMessageService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication//标准Spring Boot应用
@EnableDiscoveryClient//激活服务发现客户端
//@EnableScheduling//激活调度
@EnableFeignClients(clients = SayingService.class)
@EnableRestClient(clients = SayingRestService.class)
@EnableBinding(SimpleMessageService.class)//激活bind模式
public class SpringCloudClientApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringCloudClientApplication.class)
                .web(WebApplicationType.SERVLET)
               // .listeners(new HttpRemoteAppEventListener())//也可以使用 ListenerConfig 类的方式
                .run(args);
    }
}
