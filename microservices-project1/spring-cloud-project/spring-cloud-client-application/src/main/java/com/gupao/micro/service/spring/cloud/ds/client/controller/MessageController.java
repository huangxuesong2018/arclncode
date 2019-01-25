package com.gupao.micro.service.spring.cloud.ds.client.controller;

import com.gupao.micro.service.spring.cloud.ds.client.stream.SimpleMessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
        channel.send(new GenericMessage<>("Hello World,"+message));
        return "ok";
    }

}
