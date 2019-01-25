package com.gupao.micro.service.spring.cloud.server.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;


public interface SimpleMessageReceiver {
    @Input("channel2018")
    SubscribableChannel gupao();
}
