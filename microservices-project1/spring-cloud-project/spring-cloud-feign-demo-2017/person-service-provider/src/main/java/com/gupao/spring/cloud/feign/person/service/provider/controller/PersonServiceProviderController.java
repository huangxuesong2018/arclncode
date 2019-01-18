package com.gupao.spring.cloud.feign.person.service.provider.controller;

import com.gupao.spring.cloud.feign.api.domain.Person;
import com.gupao.spring.cloud.feign.api.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ${@link com.gupao.spring.cloud.feign.api.service.PersonService} 提供者控制器
 * 可选实现 ${@link com.gupao.spring.cloud.feign.api.service.PersonService}
 * @author HXS
 * @copyright
 * @since 2018-12-29
 */
@RestController
public class PersonServiceProviderController{
    private Map<Long,Person> persons = new ConcurrentHashMap<>();


    @PostMapping(value = "/person/save")
    boolean savePerson(@RequestBody Person person){
        return persons.put(person.getId(),person) == null;
    }

    @GetMapping(value = "/person/find/all")
    Collection<Person> findAllPersons(){
        System.out.println("---------findAllPersons");
        return persons.values();
    }
}
