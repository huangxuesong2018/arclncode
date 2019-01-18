:: Spring Boot ::        (v2.0.3.RELEASE)
spring-cloud-eureka-server-demo-2017  eureka服务发现
person-api api应用 ， person-client 和 person-service-provider 依赖些jar包
person-client 调用端
person-service-provider 服务提供方

一，服务组件(SOA)
1.注册中心 Eureka Server(spring-cloud-eureka-server-demo-2017)
   功能: 服务发现和注册
   管理页面 : http://localhost:12345/
2.Feign客户端(服务消费 person-client)
   功能: 调用Feign申明接口;
   依赖 person-api;
   注册中心注册;
3.Feign服务端(服务提供  person-server-provider)
   功能: 服务接口;
   不一定强制实现Feign申明接口;
   依赖 person-api;
   注册中心注册;
4.Feign声明接口(契约 person-api)
   功能:定义一种Java强类型接口;


二，Feign(服务调用) + Ribbon(负载均衡)
  关闭 Eureka 注册，打开Ribbon
  1. person-client application.properties 加入下面两个注释
   ## Ribbon 不使用 Eureka  使用ribbon 做负载均衡==============================
   ribbon.eureka.enabled = false
   ## 配置 "person-service" 的负载均衡服务器列表
   person-service.ribbon.listOfServers = http://localhost:9090,http://localhost:9090,http://localhost:9090
   ## Ribbon 不使用 Eureka  ==============================

  2.PersonClientApplication 启动类中
  //@EnableEurekaClient //注释 @EnableEurekaClient

  3.激活
  方式一:
  @RibbonClient(name = "person-service",configuration = MySelfRule.class)
  name = "person-service"  -> 服务提供方应用名称
  configuration = MySelfRule.class   ->配置自定义负载均衡策略的类

  方式二:
  不加这个"  @RibbonClient(...) " 配置也可以
  MySelfRule.class 类上加入 @Configuration  注解，
  然后在  MySelfRule.class 实例化  自定义负载均衡的类也可以


三，加入 Hystrix
   1.spring-cloud-feign-demo-2017 加入依赖
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
   2.person-client 中激活Hystrix
     加入注解 @EnableHystrix

   3.person-api 在需要的接口上${PersonService}指定熔断回断的实现类 fallback = PersonServiceCallback.class
    @FeignClient(value = "person-service",fallback = PersonServiceCallback.class)

   4.person-api 添加回调的实现类 ,并实现接口
    public class PersonServiceCallback implements PersonService