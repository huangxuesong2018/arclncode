package com.gupao.spring.cloud.stream.kafka.web.controller;

import com.gupao.spring.cloud.stream.kafka.stream.producer.MessageProducerBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Kafka 生产者Controller
 * Kafka 集成 Spring
 * @author HXS
 * @copyright
 * @since 2019-01-21
 */
@RestController
public class KafkaProducerController {
    /**
     * Spring + kafka的实现
     */
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final String topic;
    /**
     * Spring Cloud Stream的实现
     */
    private final MessageProducerBean messageProducerBean;

    /**
     * 通过构造器注入
     * @param kafkaTemplate 在 ${@link KafkaAutoConfiguration} 中初始化过
     * @param topic
     * @param messageProducerBean
     */
    public KafkaProducerController(KafkaTemplate<String, String> kafkaTemplate,
                                   @Value("${kafka.topic}") String topic,
                                   MessageProducerBean messageProducerBean) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
        this.messageProducerBean = messageProducerBean;
    }

    //通过Kafka kafkaTemplate 发送
    @PostMapping("/message/send")
    public Boolean sendMessage(@RequestParam String message){
         kafkaTemplate.send(topic,message);
         return true;
    }

    /**
     *  通过
     * @param message
     * @return
     */
    @GetMapping("/message/send")
    public Boolean send(@RequestParam String message){
        /**
         * topic 放到了配置
         */
        messageProducerBean.send(message);
        return true;
    }


}
