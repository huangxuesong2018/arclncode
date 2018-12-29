package com.gupao.spring.cloud.feign.client.web;

import com.gupao.spring.cloud.feign.api.domain.Person;
import com.gupao.spring.cloud.feign.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author HXS
 * @copyright
 * @since 2018-12-29
 */
@RestController
public class PersonClientController implements PersonService {
    private final PersonService personService;
    @Autowired
    public PersonClientController(PersonService personService){
        this.personService = personService;
    }


    /**
     *  保存
     * @param person
     * @return
     */
    //@PostMapping(value = "/person/save") 这里会继承 来自${@link PersonService }
    @Override
    public boolean save(Person person) {
        return personService.save(person);
    }

    @Override
    public Collection<Person> findAll() {
        return personService.findAll();
    }
}
