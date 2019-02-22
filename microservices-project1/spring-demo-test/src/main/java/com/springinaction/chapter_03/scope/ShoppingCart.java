package com.springinaction.chapter_03.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-21
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {

}
