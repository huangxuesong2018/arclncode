package com.gupao.spring.cloud.stream.kafka.stream.producer;

import com.gupao.spring.cloud.stream.kafka.stream.producer.messaging.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 消息生产者 Bean
 * @author HXS
 * @copyright
 * @since 2019-01-21
 */
@Component
@EnableBinding({Source.class, MessageSource.class})
public class MessageProducerBean {

    //====================定义标准的消息发送源 开始===========================
    @Autowired
    @Qualifier(Source.OUTPUT) //Bean的名称
    private MessageChannel messageChannel;
    @Autowired
    private Source source;

    /**
     * 发送消息
     * @param message
     */
    public void send(String message){
        doSend_3(message);
    }
    private void doSend_1(String message){
        //通过消息管道发送消息
        messageChannel.send(MessageBuilder.withPayload("[source=doSend_1]"+message).build());
    }

    private void doSend_2(String message){
        source.output().send(MessageBuilder.withPayload("[source=doSend_2]"+message).build());
    }
    //====================定义标准的消息发送源 结束===========================

    // ------------------定义自定义的消息发送源----------------------
    @Autowired
    private MessageSource messageSource;

    @Autowired
    @Qualifier(MessageSource.NAME) //Bean的名称
    private MessageChannel gupaoMessageChannel;

    private void doSend_3(String message){
        messageSource.gupao().send(MessageBuilder.withPayload("[source=doSend_3]"+message).build());
    }
    private void doSend_4(String message){
        gupaoMessageChannel.send(MessageBuilder.withPayload("[source=doSend_4]"+message).build());
    }
}
