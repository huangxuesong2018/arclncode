package com.gupao.micro.service.spring.cloud.server.controller;

import com.gupao.micro.service.spring.cloud.server.stream.SimpleMessageReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-15
 */
@RestController
public class RemoteRabbitReceiverController {

    @Autowired
    private SimpleMessageReceiver simpleMessageReceiver;


    /**
     * 接口编程,注解驱动,Spring Integration 注解驱动
     * 注意：相同的编程模型重复执行，例如 @StreamListener，不同的编程模型轮流执行
     */
    @PostConstruct //接口编程
    public void init(){
        SubscribableChannel channel =simpleMessageReceiver.gupao();
        channel.subscribe(message -> {
            MessageHeaders headers = message.getHeaders();
            String charset = (String)headers.get("charset");
            String contentType = (String)headers.get("content-type");
            byte[] context = (byte[]) message.getPayload();
            try {
                System.out.println("id=1000,接口编程:接收到消息:"+new String(context,charset));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
    }

    @StreamListener("channel2018") //Spring Cloud Stream 注解驱动
    public void onMessage(String data){
        System.out.println("id=1001,注解驱动:接收到消息:"+data);
    }

    @StreamListener("channel2018") //Spring Cloud Stream 注解驱动
    public void onMessage(byte[] data){
        System.out.println("id=1002,注解驱动:接收到消息:"+new String(data));
    }

    @ServiceActivator(inputChannel = "channel2018") //Spring Integration 注解驱动
    public void onServiceActivator(String data){
        System.out.println("id=1003,Spring Integration 注解驱动:接收到消息:"+data);
    }

    @StreamListener("test007") //Spring Cloud Stream 注解驱动
    public void onMessageFromRockMQ(byte[] data){
        System.out.println("id=1002,注解驱动:接收到消息:"+new String(data));
    }


}
