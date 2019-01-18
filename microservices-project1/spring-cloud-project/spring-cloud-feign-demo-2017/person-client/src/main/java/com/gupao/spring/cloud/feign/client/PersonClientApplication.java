package com.gupao.spring.cloud.feign.client;

import com.gupao.spring.cloud.feign.api.service.PersonService;
import com.gupao.spring.cloud.feign.client.ribbon.FirstServerForeverRule;
import com.gupao.spring.cloud.feign.client.ribbon.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author HXS
 * @copyright
 * @since 2018-12-29
 */
@SpringBootApplication
@EnableEurekaClient
//Feign服务调用
@EnableFeignClients(clients = PersonService.class)
//Ribbon(负载均衡)使用自定义的负载均衡策略
//@RibbonClient(name = "person-service",configuration = MySelfRule.class)
//激活服务熔断
@EnableHystrix
public class PersonClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonClientApplication.class,args);
    }
    @Bean
    public FirstServerForeverRule firstServerForeverRule(){
        return new FirstServerForeverRule();
    }
}
