Spring Cloud Stream Rabbit
demo访问:http://localhost:8080/stream/send?message=33

1. windows 安装 （D:\Program Files\RabbitMQ Server\rabbitmq_server-3.7.10\sbin）
#### rabbitMQ 启动命令行： Net stop rabbitmq && net start rabbitmq
#### 网址: http://localhost:15672，用户名：guest，密码：guest
### 配置  生产者  destination = topic


2. application.properties 配置【Spring Cloud 客户端应用,服务端应用】
 # spring.cloud.stream.rabbit.${channel-name}.destination
   spring.cloud.stream.rabbit.channel2018.destination=test2018

3.加入 Spring Cloud Stream的依赖【Spring Cloud 客户端应用,服务端应用】
 <!-- rabbit MQ-->
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-stream-binder-rabbit</artifactId>
     </dependency>
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
     </dependency>
 <!-- rabbit MQ-->



