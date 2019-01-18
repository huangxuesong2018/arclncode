package com.gupao.micro.service.spring.cloud.ds.client.annotation;

import java.lang.annotation.*;

/**
 * 自定义 RestClient 注解
 * RestClient
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface RestClient {
    /**
     * REST服务应用名称
     * @return
     */
    String name();
}
