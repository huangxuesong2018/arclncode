package com.gupao.micro.service.spring.cloud.ds.client.controller;

import com.gupao.micro.service.spring.cloud.ds.client.stream.SimpleMessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * 配合 rabbitmq
 * @author HXS
 * @copyright
 * @since 2019-01-24
 */
@RestController
public class MessageController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private SimpleMessageService simpleMessageService;

    @GetMapping("/send")
    public String send(@RequestParam String message){
        rabbitTemplate.convertAndSend("message");
        return "ok";
    }

    @GetMapping("/stream/send")
    public String streamSend(@RequestParam String message){
        MessageChannel channel = simpleMessageService.gupao();
        Map<String,String> header = new HashMap<>();
        header.put("charset","UTF-8");
        header.put("content-type", MediaType.TEXT_MARKDOWN_VALUE);
        GenericMessage message1 = new GenericMessage(message,header);
        channel.send(message1);
        return "ok";
    }

    @GetMapping("/stream/send/rocketmq")
    public String streamSendToRocketMQ(@RequestParam String message){
        MessageChannel channel = simpleMessageService.testChanel();
        GenericMessage message1 = new GenericMessage(message);
        channel.send(message1);
        return "ok";
    }
}
