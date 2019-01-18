package com.gupao.spring.cloud.feign.api.service;

import com.gupao.spring.cloud.feign.api.domain.Person;
import com.gupao.spring.cloud.feign.api.hystrix.PersonServiceCallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 *  ${@link com.gupao.spring.cloud.feign.api.domain.Person} 服务
 * @author HXS
 * @copyright
 * @since 2018-12-29
 */
@FeignClient(value = "person-service",fallback = PersonServiceCallback.class)  // 服务提供方应用的名称
public interface PersonService {
    @PostMapping(value = "/person/save")
    boolean save(Person person);

    @GetMapping(value = "/person/find/all")
    Collection<Person> findAll();
}
