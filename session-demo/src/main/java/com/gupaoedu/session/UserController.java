package com.gupaoedu.session;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @GetMapping("/index")
    public String index(HttpServletRequest request){
        request.getSession().setAttribute("name","huang");
        return "success";
    }

    @GetMapping("/get")
    public String get(HttpServletRequest request){
        String str = (String)request.getSession().getAttribute("name");
        return str;
    }
}
