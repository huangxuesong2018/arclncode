package com.gupao.spring.cloud.feign.person.service.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * ${@link com.gupao.spring.cloud.feign.api.service.PersonService} 提供者
 * @author HXS
 * @copyright
 * @since 2018-12-29
 */
@SpringBootApplication
@EnableEurekaClient
public class PersonServiceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonServiceProviderApplication.class,args);
    }
}
