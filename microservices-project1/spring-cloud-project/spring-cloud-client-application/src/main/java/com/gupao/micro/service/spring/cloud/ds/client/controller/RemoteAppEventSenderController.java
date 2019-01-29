package com.gupao.micro.service.spring.cloud.ds.client.controller;

import com.gupao.micro.service.spring.cloud.client.event.RemoteAppEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 远程应用事件控制器
 * Spring Cloud Bus(事件总线)
 * @author HXS
 * @copyright
 * @since 2019-01-28
 */
@RestController
public class RemoteAppEventSenderController implements ApplicationEventPublisherAware{
    private ApplicationEventPublisher publisher;

    @Value("${spring.application.name}")
    private String currentAppName;
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * http://localhost:8080/send/remote/event?message=555
     * @param message
     * @return
     */
    @GetMapping("/send/remote/event")
    public String sendEvent(@RequestParam String message){
        publisher.publishEvent(message);
        return "sent";
    }

    /**
     * 对一个集群发
     * http://localhost:8080/send/remote/event/spring-cloud-server-application
     * @param appName
     * @param data
     * @return
     */
    @PostMapping("/send/remote/event/{appName}")
    public String sendAppCluster(@PathVariable String appName,@RequestBody Map data){
        //发送到自已
        List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(appName);
        //构建事件
        RemoteAppEvent remoteAppEvent = new RemoteAppEvent(data,currentAppName,appName,serviceInstances);
        //发送事件， 发给当前的上下文
        publisher.publishEvent(remoteAppEvent);
        return "ok";
    }

    /**
     * 对一个实例发
     * @param appName
     * @param data
     * @return
     */
    @PostMapping("/send/remote/event/{appName}/{ip}/{port}")
    public String sendAppCluster(@PathVariable String appName,
                                 @PathVariable String ip,
                                 @PathVariable int port,
                                 @RequestBody Object data){
        ServiceInstance serviceInstances = new DefaultServiceInstance(appName,ip,port,false);
        //构建事件
        RemoteAppEvent remoteAppEvent = new RemoteAppEvent(data,currentAppName,appName, Arrays.asList(serviceInstances));
        //发送事件， 发给当前的上下文
        publisher.publishEvent(remoteAppEvent);
        return "ok";
    }

    /**
     * 注入ApplicationEventPublisher
     * @param applicationEventPublisher
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        publisher = applicationEventPublisher;
    }
}
