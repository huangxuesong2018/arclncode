package com.gupao.spring.cloud.stream.kafka.stream.producer.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessageSource {
    /**
     * 消息来源的管道名称:"gupao"
     */
    String NAME = "gupao";

    @Output(NAME)
    MessageChannel gupao();
}
