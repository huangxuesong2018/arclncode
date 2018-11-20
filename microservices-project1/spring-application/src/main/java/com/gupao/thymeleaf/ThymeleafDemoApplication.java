package com.gupao.thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@UserTag(id = "aa")
@UserTag(id = "bbb")
public class ThymeleafDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThymeleafDemoApplication.class,args);
    }
}
