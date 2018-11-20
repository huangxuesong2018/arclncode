package com.gupao.thymeleaf;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface UserTags {
    UserTag[] value();
}
