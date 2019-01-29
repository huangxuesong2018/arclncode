package com.gupao.micro.service.spring.cloud.ds.client.bean;

import com.gupao.micro.service.spring.cloud.client.event.HttpRemoteAppEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringBoot项目的Bean装配默认规则是根据
 *  Application类{@link com.gupao.micro.service.spring.cloud.ds.client.SpringCloudClientApplication}
 * 所在的包位置从上往下扫描！ “Application类”是指SpringBoot项目入口类。
 * 这个类的位置很关键：
 * @author HXS
 * @copyright
 * @since 2019-01-29
 */
@Configuration
public class ListenerConfig {
    @Bean
    public HttpRemoteAppEventListener httpRemoteAppEventListener(){
        return new HttpRemoteAppEventListener();
    }
}
