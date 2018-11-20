package com.gupao.thymeleaf.t;

import java.lang.annotation.*;

@Repeatable(Authoritys.class)
public @interface Authority {
    String role();
}
