package com.gupao.spring.cloud.feign.client.ribbon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MySelfRule ，TwoRule只能同时存在一个
 * @author HXS
 * @copyright
 * @since 2019-01-02
 */
//@Configuration
public class TwoRule {
    @Bean
    public TwoServerForeverRule twoServerForeverRule(){
        return new TwoServerForeverRule();
    }
}
