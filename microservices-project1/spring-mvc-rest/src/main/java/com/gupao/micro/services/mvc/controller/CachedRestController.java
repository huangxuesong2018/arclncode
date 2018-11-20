package com.gupao.micro.services.mvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CachedRestController {
    @ResponseBody//这个地方肯定没有办法缓存
    @RequestMapping("/hello")
    public String helloWorld(){
        System.out.println("->helloWorld 2");
        return "Hello World 2";//body为string
    }

    @RequestMapping("/hello2")
    public ResponseEntity<String> cache(@RequestParam(required = false,defaultValue = "false") boolean cached){
        System.out.println("->hello3->"+cached);
        if(cached){
            return  new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }else{
            return ResponseEntity.ok("Hello,World");
        }
    }
}
