package com.gupao.micro.service.spring.cloud.server.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SemaphoreCircuitZBreaker {
    /**
     *  信号量
     * @return  设置超时时间
     */
    int value();
}
