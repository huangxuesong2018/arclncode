package com.gupao.micro.service.spring.cloud.server.controller;

import com.gupao.micro.service.spring.cloud.server.annotation.TimeoutCircuitBreaker;
import com.gupao.micro.service.spring.cloud.server.annotation.SemaphoreCircuitZBreaker;
import com.gupao.micro.service.spring.cloud.server.annotation.TimeoutCircuitBreaker;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.*;

@RestController
@EnableHystrix//激活Hystrix
public class ServerController {

    //取到应用的名称
    @Value("${spring.application.name}")
    private String currentServiceName;

    private Random random = new Random();
    //第一个版本: 利用 Hystrix 熔断
    // 如果超过100毫秒则让它输出
    @HystrixCommand(
            fallbackMethod = "errorContent", //熔错方法
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "100")
            }
    )
    @RequestMapping("/say")
    public String say(@RequestParam String message) throws InterruptedException {
        return doSay(message);
    }
    public String errorContent(String message){
        return "Fault";
    }

    //第二个版本: 利用RestControllerAdvice实现
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    @RequestMapping("/middleSay")
    public String middleSay(@RequestParam(value = "") String message) throws InterruptedException, ExecutionException, TimeoutException {
        Future<String> future = executorService.submit(()->{
            return doSay(message);
        });
        // 100 毫秒超时
        String returnValue = null;

        try {
            returnValue = future.get(100, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            future.cancel(true); // 取消执行
            throw e;
        }
        return returnValue;
    }


    //第三个版本: 高级版本 使用切面
    @RequestMapping("/advancedSay")
    public String advancedSay(@RequestParam(value = "") String message) throws InterruptedException, ExecutionException, TimeoutException {
        return doSay(message+"->advancedSay");
    }


    //第四个版本: 高级版本 使用注解
    @RequestMapping("/advancedSay2")
    @TimeoutCircuitBreaker(timeout = 100 )
    public String advancedSay2(@RequestParam(value = "") String message) throws InterruptedException, ExecutionException, TimeoutException {
        return doSay(message+"->advancedSay2");
    }

    //第五个版本: 高级版本 使用注解（信号量）
    @RequestMapping("/advancedSay3")
    @SemaphoreCircuitZBreaker(value = 5 )
    public String advancedSay3(@RequestParam(value = "") String message) throws InterruptedException, ExecutionException, TimeoutException {
        return doSay(message+"->advancedSay2");
    }

    private String doSay(String message) throws InterruptedException {
        //模拟时间大于 100，触发容错
        int value = random.nextInt(200);
        System.out.println("say() costs "+value+" ms");
        Thread.sleep(value);
        System.out.println("执行了操作,"+message);
        return "Hello,"+message;
    }
}
