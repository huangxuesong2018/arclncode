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
                System.out.println("第1种:接收到消息:"+new String(context,charset));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
    }

    @StreamListener("channel2018") //Spring Cloud Stream 注解驱动
    public void onMessage(String data){
        System.out.println("第2种:接收到消息:"+data);
    }

    @StreamListener("channel2018") //Spring Cloud Stream 注解驱动
    public void onMessage(byte[] data){
        System.out.println("第3种:接收到消息:"+new String(data));
    }

    @ServiceActivator(inputChannel = "channel2018") //Spring Integration 注解驱动
    public void onServiceActivator(String data){
        System.out.println("第4种:接收到消息:"+data);
    }
}
