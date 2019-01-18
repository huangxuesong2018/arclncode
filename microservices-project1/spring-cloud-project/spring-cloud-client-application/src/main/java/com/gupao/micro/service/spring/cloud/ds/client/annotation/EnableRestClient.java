package com.gupao.micro.service.spring.cloud.ds.client.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *  自定义 Feign
 *  激活
 * @author HXS
 * @copyright
 * @since 2019-01-07
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RestClientsRegistrar.class)
public @interface EnableRestClient {
    Class<?>[] clients() default {};
}
