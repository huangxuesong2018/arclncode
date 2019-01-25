package com.gupao.spring.cloud.stream.kafka.stream.consumer;

import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 异步接收消息
 * @author HXS
 * @copyright
 * @since 2019-01-22
 */
@Component
@EnableBinding(Sink.class)
public class MessageConsumerBean {

    @Autowired
    @Qualifier(Sink.INPUT)
    private SubscribableChannel subscribableChannel;

    @Autowired
    private Sink sink;
    /**
     *
     * 这里的监听 与 {@link org.springframework.kafka.annotation.KafkaListener} 互斥
     *  一个地方消费了，另一个地方就不会再消费了，只消费一次
     * @PostConstruct PostConstruct 注释用于在依赖关系注入完成之后需要执行的方法上，以执行任何初始化。
     * 此方法必须在将类放入服务之前调用。支持依赖关系注入的所有类都必须支持此注释。
     * 即使类没有请求注入任何资源，用 PostConstruct 注释的方法也必须被调用。只有一个方法可以用此注释
     * 进行注释。应用 PostConstruct 注释的方法必须遵守以下所有标准：该方法不得有任何参数，
     * 除非是在 EJB 拦截器 (interceptor) 的情况下，根据 EJB 规范的定义，
     * 在这种情况下它将带有一个 InvocationContext 对象 ；该方法的返回类型必须为 void；
     * 该方法不得抛出已检查异常；应用 PostConstruct 的方法可以是 public、protected、package private
     * 或 private；除了应用程序客户端之外，该方法不能是 static；该方法可以是 final；
     * 如果该方法抛出未检查异常，那么不得将类放入服务中，除非是能够处理异常并可从中恢复的 EJB
     */
    @PostConstruct
    public void init(){
        subscribableChannel.subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println(message.getHeaders()+","+message.getPayload());
            }
        });
    }

    //第2种实现，通过 @ServiceActivator
    @ServiceActivator(inputChannel = Sink.INPUT)
    public void onMessage(Object message){
        System.out.println("通过 @ServiceActivator 监听消息"+message);
    }

    //第3种实现，通过 @ServiceActivator
    @StreamListener( Sink.INPUT)
    public void onMessage(String message){
        System.out.println("通过 @StreamListener 监听消息"+message);
    }
}
