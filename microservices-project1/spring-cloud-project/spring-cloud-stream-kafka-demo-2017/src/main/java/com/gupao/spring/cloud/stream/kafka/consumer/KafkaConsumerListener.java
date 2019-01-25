package com.gupao.spring.cloud.stream.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 *  和{@link com.gupao.spring.cloud.stream.kafka.stream.consumer.MessageConsumerBean} 互斥
 * 消费者 监听器  Spring kafka的实现
 * @author HXS
 * @copyright
 * @since 2019-01-21
 */
@Component
public class KafkaConsumerListener {

    @KafkaListener(topics = "${kafka.topic}")
    public void onMessage(String message){
        System.out.println("[${kafka.topic}]Kafka 消费者监听器，接收到消息:"+message);
    }


    @KafkaListener(topics = "gupao")
    public void onGupaoMessage(String message){
        System.out.println("[gupao]Kafka 消费者监听器，接收到消息:"+message);
    }
}
