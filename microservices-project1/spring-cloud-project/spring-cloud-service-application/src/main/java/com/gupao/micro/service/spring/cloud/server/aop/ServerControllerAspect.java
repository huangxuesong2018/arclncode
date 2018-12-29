package com.gupao.micro.service.spring.cloud.server.aop;


import com.gupao.micro.service.spring.cloud.server.annotation.TimeoutCircuitBreaker;
import com.gupao.micro.service.spring.cloud.server.controller.ServerController;
import org.apache.catalina.Executor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 *  {@link ServerController}的切面
 */
@Aspect
@Component
public class ServerControllerAspect {
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Around("execution(* com.gupao.micro.service.spring.cloud." +
            "server.controller.ServerController.advancedSay(..)) && args(message)")
    public Object advancedSayTimeOut(ProceedingJoinPoint point,String message) throws Throwable {
        return doInvoke(point,message,100);
    }

    public Object doInvoke(ProceedingJoinPoint point,String message, long timeout) throws ExecutionException, InterruptedException {
        Future future = executorService.submit(()->{
            Object returnValue = null;
            try {
                returnValue = point.proceed(new Object[]{message});
            } catch (Throwable throwable) {
            }
            return returnValue;
        });
        Object returnValue = null;
        try{
            returnValue =  future.get(timeout, TimeUnit.MILLISECONDS);
        }catch (TimeoutException e){
            future.cancel(true);
            returnValue = errorContent("");
        }
        return returnValue;
    }


/*    @Around("execution(* com.gupao.micro.service.spring.cloud." +
            "server.controller.ServerController.advancedSay2(..)) && args(message) && @annotation(circuitBreaker)")
    public Object advancedSay2TimeOut(ProceedingJoinPoint point, String message, CircuitBreaker circuitBreaker) throws Throwable {
        long time =  circuitBreaker.timeout();
        return doInvoke(point,message,time);
    }*/


    @Around("execution(* com.gupao.micro.service.spring.cloud." +
            "server.controller.ServerController.advancedSay2(..)) && args(message)")
    public Object advancedSay2TimeOut(ProceedingJoinPoint point, String message) throws Throwable {
        long timeout =  -1;
        if(point instanceof MethodInvocationProceedingJoinPoint){
            MethodInvocationProceedingJoinPoint methodPoint = (MethodInvocationProceedingJoinPoint)point;
            MethodSignature signature = (MethodSignature) methodPoint.getSignature();
            Method method = signature.getMethod();
            TimeoutCircuitBreaker circuitBreaker = method.getAnnotation(TimeoutCircuitBreaker.class);
            timeout = circuitBreaker.timeout();
        }
        return doInvoke(point,message,timeout);
    }


    @Around("execution(* com.gupao.micro.service.spring.cloud." +
            "server.controller.ServerController.advancedSay2(..)) && args(message)")
    public Object advancedSay2Semaphore(ProceedingJoinPoint point, String message) throws Throwable {
        long timeout =  -1;
        if(point instanceof MethodInvocationProceedingJoinPoint){
            MethodInvocationProceedingJoinPoint methodPoint = (MethodInvocationProceedingJoinPoint)point;
            MethodSignature signature = (MethodSignature) methodPoint.getSignature();
            Method method = signature.getMethod();
            TimeoutCircuitBreaker circuitBreaker = method.getAnnotation(TimeoutCircuitBreaker.class);
            timeout = circuitBreaker.timeout();
        }
        return doInvoke(point,message,timeout);
    }

    private String errorContent(String message){
        return "Fault";
    }
}
