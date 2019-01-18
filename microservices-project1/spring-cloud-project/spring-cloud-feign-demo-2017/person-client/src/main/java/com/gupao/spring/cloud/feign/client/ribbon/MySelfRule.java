package com.gupao.spring.cloud.feign.client.ribbon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HXS
 * @copyright
 * @since 2019-01-02
 */
public class MySelfRule {
    @Bean
    public FirstServerForeverRule firstServerForeverRule(){
        return new FirstServerForeverRule();
    }
}
