应用功能介绍
一，spring-cloud-client-application
   1.客户端节点
   2.注册到 Zookeeper
   3.利用RestTemplate 实现服务调用
   4.从Zookeeper中获取服务节点，自定义负载均衡实现
   5.添加Spring cloud feign 服务调用
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-openfeign</artifactId>
           </dependency>

   6.自定义实现服务调用(入口http://localhost:8080/rest/say?message=hello)
      ->借助ImportBeanDefinitionRegistrar接口实现bean的动态注入,生成接口的代理对象
      在代理对象中 利用RestTemplate实现远程调用


二，spring-cloud-service-application
   1.服务端节点
   2.注册到 Zookeeper
   3.为客户端提拱服务实现
   4.Hystrix服务熔断
   5.自定义服务容错实现
      ->利用Future 实现超时容错机制
      ->利用Semaphore 实现访问量容错机制

 三,spring-cloud-servlet-gateway 网关
    spring-cloud-service-application(服务提供) ->注册到zookeeper
    spring-cloud-servlet-gateway  ->注册到zookeeper
    访问测试: http://127.0.0.1:20000/gateway/spring-cloud-server-application/say?message=arcln

 四，spring-cloud-stream-kafka-demo-2017  kafka+stream
