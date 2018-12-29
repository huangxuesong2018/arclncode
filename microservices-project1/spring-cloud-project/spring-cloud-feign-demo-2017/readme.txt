:: Spring Boot ::        (v2.0.3.RELEASE)
服务组件(SOA)
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