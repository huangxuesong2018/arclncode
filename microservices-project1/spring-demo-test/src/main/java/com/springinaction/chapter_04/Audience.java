package com.springinaction.chapter_04;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 使用注解定义切面
 * @author HXS
 * @copyright
 * @since 2019-02-22
 */
@Aspect
@Component
public class Audience {

    @Pointcut("execution(* com.springinaction.chapter_04.Performance.perform())")
    public void performance(){}

    @Around("performance()")
    public void watchPerformance(ProceedingJoinPoint joinPoint){
        try {
            System.out.println("表演之前..");
            joinPoint.proceed();
            System.out.println("表演之后..");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    //带参数的方法
    @Pointcut("execution(* com.springinaction.chapter_04.Performance.perform(String)) " +
            " && args(title)" )
    public void order(String title){};

    @Around("order(title)")
    public void watchPerformanceOrder(ProceedingJoinPoint joinPoint,String title){
        try {
            System.out.println("之前..."+title);
            joinPoint.proceed();
            System.out.println("之后...");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

/*    @Before("performance()")
    public void silenceCellPhones(){
        System.out.println("silence cell phones");
    }

    @Before("performance()")
    public void takeSeats(){
        System.out.println("taking seats");
    }

    @AfterReturning("performance()")
    public void applause(){
        System.out.println("applause");
    }

    @AfterThrowing("performance()")
    public void demandRefund(){
        System.out.println("demandRefund");
    }*/
}
