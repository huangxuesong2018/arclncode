package com.springinaction.chapter_03.qualifier;

import com.springinaction.chapter_03.qualifier.service.Dessert;
import com.springinaction.chapter_03.qualifier.service.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanConfig.class)
public class QualifierTest {
    @Autowired
    private Person boy;

    @Test
    public void t(){
        boy.eat();
    }
}
