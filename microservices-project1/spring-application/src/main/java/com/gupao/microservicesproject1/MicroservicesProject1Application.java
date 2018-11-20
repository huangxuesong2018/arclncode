package com.gupao.microservicesproject1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
public class MicroservicesProject1Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = test(args);
		System.out.println(context.getBean(MicroservicesProject1Application.class));
		System.out.println("当前spring 应用 上下文的类:"+context.getClass().getName());
	}


	/**
     * 第一种启动方法
	 * @param args
	 */
	public static ConfigurableApplicationContext first(String[] args){
		return SpringApplication.run(MicroservicesProject1Application.class, args);
	}

	/**
	 * 第二种启动方法
	 * @param args
	 */
	public static ConfigurableApplicationContext two(String[] args){
		return new SpringApplicationBuilder(MicroservicesProject1Application.class)
				.properties("server.port=0")//server.port=0 随机要操作系统要一个端口
				.run(args);
	}
	//与two 等价，写起来比较麻烦
	public static ConfigurableApplicationContext two1(String[] args){
		SpringApplication springApplication = new SpringApplication(MicroservicesProject1Application.class);
		Map<String,Object> properties = new LinkedHashMap<String ,Object>();
		properties.put("server.port",0);
		springApplication.setDefaultProperties(properties);
		return springApplication.run(args);
	}


	/**
	 *  设置为非web应用
	 * @return
	 */
	public static ConfigurableApplicationContext test(String[] args){
		SpringApplication springApplication = new SpringApplication(MicroservicesProject1Application.class);
		Map<String,Object> properties = new LinkedHashMap<String ,Object>();
		properties.put("server.port",0);
		springApplication.setDefaultProperties(properties);
		springApplication.setWebApplicationType(WebApplicationType.NONE);
		return springApplication.run(args);
	}
}
