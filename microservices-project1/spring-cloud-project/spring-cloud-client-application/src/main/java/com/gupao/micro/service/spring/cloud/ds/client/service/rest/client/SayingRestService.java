package com.gupao.micro.service.spring.cloud.ds.client.service.rest.client;

import com.gupao.micro.service.spring.cloud.ds.client.annotation.RestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
