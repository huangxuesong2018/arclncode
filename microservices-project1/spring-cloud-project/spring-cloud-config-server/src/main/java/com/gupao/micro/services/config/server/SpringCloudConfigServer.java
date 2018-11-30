package com.gupao.micro.services.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
//这个注解就是开启 Spring cloud config server
@EnableConfigServer
public class SpringCloudConfigServer {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigServer.class,args);
    }

/*  自定义的数据源 注入 EnvironmentRepository实例即可
    @Bean
    public EnvironmentRepository environmentRepository(){
        return (String application, String profile, String label) ->{
            Environment environment = new Environment("default",profile);
            List<PropertySource> propertySources =  environment.getPropertySources();
            Map<String,String> sources = new HashMap<String,String>();
            sources.put("name","自定义实现");
            PropertySource e = new PropertySource("map",sources);
            propertySources.add(e);
            return environment;
        };
    }*/
}
