package com.gupao.micro.service.spring.cloud.client.event;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class HttpRemoteAppEventListener implements ApplicationListener<RemoteAppEvent> {

    private RestTemplate template = new RestTemplate();

    @Override
    public void onApplicationEvent(RemoteAppEvent event) {
        System.out.println("监听到................");
        Object source = event.getSource();
        String appName = event.getAppName();
        List<ServiceInstance> serviceInstances = event.getServiceInstances();
        for (ServiceInstance s:serviceInstances) {
            String rootUrl = s.isSecure() ?
                    "https://"+s.getHost()+":"+s.getPort():
                    "http://"+s.getHost()+":"+s.getPort();
            String url = rootUrl + "/receive/remote/event";
            Map<String,Object> data = new HashMap<>();
            data.put("sender",event.getSender());
            data.put("value",source);
            data.put("type",RemoteAppEvent.class.getName());
            String responseContent = template.postForObject(url,data,String.class);
            System.out.println("url;"+url+",请求响应："+responseContent);
        }
    }
}
