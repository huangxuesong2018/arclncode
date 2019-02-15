package com.gupao.micro.service.spring.cloud.server;

import com.gupao.micro.service.spring.cloud.server.stream.SimpleMessageReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.SubscribableChannel;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(SimpleMessageReceiver.class)//激活并引入 SimpleMessageReceiver
public class SpringCloudServerApplication {
    public static void main(String[] args) {
       /* new SpringApplicationBuilder(SpringCloudServerApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);*/
        SpringApplication.run(SpringCloudServerApplication.class,args);
    }

}
