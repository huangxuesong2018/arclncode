package com.gupao.micro.service.spring.cloud.client.event;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * {@link RemoteAppEvent} 监听器，将事件数据发送HTTP请求到目标机器
 *  事件三步曲: {
 *      1->定义事件{@link RemoteAppEvent},
 *      2->发布事件{@link com.gupao.micro.service.spring.cloud.ds.client.controller.RemoteAppEventSenderController},
 *      3->监听事件{@link HttpRemoteAppEventListener}
 *      }
 * @author HXS
 * @copyright
 * @since 2019-01-28
 */
public class HttpRemoteAppEventListener implements SmartApplicationListener {

    private RestTemplate template = new RestTemplate();
    private DiscoveryClient discoveryClient;
    private String currentAppName;

    public void onApplicationEvent(RemoteAppEvent event) {
        Object source = event.getSource();
        String appName = event.getAppName();
        /**
         * 依赖于Zookeeper注册中心
         * 从Zookeeper注册中心 发现服务列表
         */
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(appName);
        for (ServiceInstance s:serviceInstances) {
            String rootUrl = s.isSecure() ?
                    "https://"+s.getHost()+":"+s.getPort():
                    "http://"+s.getHost()+":"+s.getPort();
            String url = rootUrl + "/receive/remote/event";
            Map<String,Object> data = new HashMap<>();
            data.put("sender",this.currentAppName);
            data.put("value",source);
            data.put("type",RemoteAppEvent.class.getName());
            String responseContent = template.postForObject(url,data,String.class);
            System.out.println("url;"+url+",请求响应："+responseContent);
        }
    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return RemoteAppEvent.class.isAssignableFrom(eventType)
                || ContextRefreshedEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        System.out.println("------------->SourceType->"+sourceType.getSimpleName());
        return true;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof RemoteAppEvent){
            System.err.println("------------->RemoteAppEvent");
            onApplicationEvent((RemoteAppEvent)event);
        }else if (event instanceof ContextRefreshedEvent){
            System.err.println("------------->ContextRefreshedEvent");
            onContextRefreshedEven((ContextRefreshedEvent)event);
        }
    }
    private void onContextRefreshedEven(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        this.discoveryClient = context.getBean(DiscoveryClient.class);
        this.currentAppName = context.getEnvironment().getProperty("spring.application.name");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
