package com.gupao.micro.service.spring.cloud.stream.binder.rocketmq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-15
 */
@Configuration
public class RocketMQMessageChannelBinderConfiguration {
    @Bean
    public RocketMQMessageChannelBinder rocketMQMessageChannelBinder(){
        return new RocketMQMessageChannelBinder();
    }

}
