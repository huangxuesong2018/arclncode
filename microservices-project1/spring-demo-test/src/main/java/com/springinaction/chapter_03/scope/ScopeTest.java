package com.springinaction.chapter_03.scope;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanConfig.class)
public class ScopeTest {
    @Autowired
    private StoreService storeService;
    @Autowired
    private ShopService shopService;

    @Test
    public void test(){
        storeService.buy();
        shopService.buy();
    }
}
