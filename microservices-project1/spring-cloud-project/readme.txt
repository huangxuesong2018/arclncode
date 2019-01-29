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

   7.加入 spring-cloud-stream-rabbiMQ  消息生产者
    添加依赖
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-stream-binder-rabbit</artifactId>
     </dependency>
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
     </dependency>

     8.利用ApplicationEvent 实现调用消息发送

二，spring-cloud-service-application
   1.服务端节点
   2.注册到 Zookeeper
   3.为客户端提拱服务实现
   4.Hystrix服务熔断
   5.自定义服务容错实现
      ->利用Future 实现超时容错机制
      ->利用Semaphore 实现访问量容错机制
   6.加入 spring-cloud-stream-rabbiMQ  消息消费者

   7.利用ApplicationEvent 实现消息接收

 三,spring-cloud-servlet-gateway 网关
    spring-cloud-service-application(服务提供) ->注册到zookeeper
    spring-cloud-servlet-gateway  ->注册到zookeeper
    访问测试: http://127.0.0.1:20000/gateway/spring-cloud-server-application/say?message=arcln

 四，spring-cloud-stream-kafka-demo-2017(2017-spring Stream(上1))  kafka+stream
   1.kafka配置(../config/server.properties)
     broker.id=0
     zookeeper.connect=192.168.1.130:2181
     listeners=PLAINTEXT://192.168.1.130:9092
   2.启动 指定配置文件
     需要先启动zookeeper
     bin/kafka-server-start.sh config/server.properties &

   3.Spring Boot Kafka
      -> application.properties 加入kafka生产者和消费者的配置
      -> KafkaTemplate  生产者发送消息 (Spring boot里面的)
      -> @KafkaListener  监听消息（org.springframework.kafka.annotation里面的）

