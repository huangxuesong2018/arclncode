package com.gupao.spring.cloud.feign.client;

import com.gupao.spring.cloud.feign.api.service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author HXS
 * @copyright
 * @since 2018-12-29
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableFeignClients(clients = PersonService.class)
public class PersonClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonClientApplication.class,args);
    }
}
