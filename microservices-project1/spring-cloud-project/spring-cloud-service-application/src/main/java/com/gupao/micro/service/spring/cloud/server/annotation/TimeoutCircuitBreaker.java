package com.gupao.micro.service.spring.cloud.server.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimeoutCircuitBreaker {
    long timeout();
}
