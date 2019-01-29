package com.gupao.micro.service.spring.cloud.server.controller;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 远程事件接收器
 * @author HXS
 * @copyright
 * @since 2019-01-29
 */
@RestController
public class RemoteAppEventReceiverController implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;

    @PostMapping("/receive/remote/event")
    public String receive(@RequestBody Map<String,Object> data ){
        String sender = (String)data.get("sender");
        Object value = data.get("value");
        String type = (String)data.get("type");
        //接收到对象内容，同样也要发送事件到本地，做处理
        publisher.publishEvent(new SenderRemoteAppEvent(sender,value));
        return "received";
    }

    /**
     * 发送者事件
     */
    private static class SenderRemoteAppEvent extends ApplicationEvent{
        private final String sender;
        public SenderRemoteAppEvent(String sender,Object source) {
            super(source);
            this.sender = sender;
        }

        public String getSender() {
            return sender;
        }
    }

    @EventListener
    public void onEvent(SenderRemoteAppEvent event){
        System.out.println("接收到事件源："+event+",应用 "+event.getSender());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
