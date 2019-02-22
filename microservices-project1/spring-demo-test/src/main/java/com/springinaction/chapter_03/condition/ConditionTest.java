package com.springinaction.chapter_03.condition;

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
public class ConditionTest {
    @Autowired
    private MagicBean magic;

    @Test
    public void t(){
        System.out.println(magic);
    }
}
