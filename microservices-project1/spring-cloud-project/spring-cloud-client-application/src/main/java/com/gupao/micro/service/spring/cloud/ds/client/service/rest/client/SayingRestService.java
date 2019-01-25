package com.gupao.micro.service.spring.cloud.ds.client.service.rest.client;

import com.gupao.micro.service.spring.cloud.ds.client.annotation.RestClient;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * @author HXS
 * @copyright
 * @since 2019-01-07
 */
@RestClient(name="${saying.rest.service.name}")
public interface SayingRestService {
    @GetMapping("/say")
    public String say(@RequestParam(value = "message") String message);

    @GetMapping("/say2")
    public String say2(@RequestParam(value = "message") String message,@RequestParam(value = "name") String name );

    public static void main(String[] args) {
        Method method = ReflectionUtils.findMethod(SayingRestService.class,"say",String.class);
        ParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        Stream.of(discoverer.getParameterNames(method)).forEach(System.out::println);
    }
}
