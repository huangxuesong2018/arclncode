package com.gupao.micro.service.spring.cloud.ds.client.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 可以做为 Kafka 和 rabbitMQ
 * @author HXS
 * @copyright
 * @since 2019-01-25
 */
public interface SimpleMessageService {


    @Output("channel2018")// channel name
    MessageChannel gupao(); //destination = test2018
}
