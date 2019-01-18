package com.gupao.micro.service.spring.cloud.ds.client.service.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author HXS
 * @copyright
 * @since 2019-01-07
 */
@FeignClient(name = "spring-cloud-server-application")
public interface SayingService {
    /** 之前的调用方式
     * 服务调用  (RestTemplate+@LoadBalanced )
     * 得用  @LoadBalanced 做负载均衡
     */
    /*
      @GetMapping("/lb/invoke/{serviceName}/say")
          public String lbInvokeSay(@PathVariable String serviceName, @RequestParam String message){
         return lbRestTemplate.getForObject("http://"+serviceName+"/say?message="+message,String.class);
      }
    */


    @GetMapping("/say")
    public String say(@RequestParam(value = "message") String message);
}
