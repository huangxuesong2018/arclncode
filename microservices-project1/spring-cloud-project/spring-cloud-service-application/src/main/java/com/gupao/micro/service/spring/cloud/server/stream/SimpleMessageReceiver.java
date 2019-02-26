package com.gupao.micro.service.spring.cloud.server.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


public interface SimpleMessageReceiver {
    @Input("channel2018")
    SubscribableChannel gupao();


    @Input("test007")
    SubscribableChannel testChanel();
}
