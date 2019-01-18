package com.gupao.spring.cloud.feign.api.hystrix;

import com.gupao.spring.cloud.feign.api.domain.Person;
import com.gupao.spring.cloud.feign.api.service.PersonService;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author HXS
 * @copyright
 * @since 2019-01-03
 */
@Component
public class PersonServiceCallback implements PersonService{
    @Override
    public boolean save(Person person) {
        System.out.println("服务熔断......");
        return false;
    }

    @Override
    public Collection<Person> findAll() {
        System.out.println("服务熔断......");
        return null;
    }
}
