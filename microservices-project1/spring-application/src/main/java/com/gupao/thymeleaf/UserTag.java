package com.gupao.thymeleaf;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Repeatable(UserTags.class)//java 8
public @interface UserTag {
    String id() default "";
}
