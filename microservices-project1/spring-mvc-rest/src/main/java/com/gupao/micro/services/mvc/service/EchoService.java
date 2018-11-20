package com.gupao.micro.services.mvc.service;

import com.gupao.micro.services.mvc.annotation.TransactionalService;

@TransactionalService(value = "echo-2018",txName = "myTxName")
public class EchoService {
    public void echo(String msg){
        try{
            System.out.println(msg);
            int i = 1/0;
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
