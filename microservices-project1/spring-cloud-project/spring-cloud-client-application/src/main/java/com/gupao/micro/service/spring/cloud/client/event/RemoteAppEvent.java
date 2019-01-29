package com.gupao.micro.service.spring.cloud.client.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationEvent;

import java.util.List;
import java.util.Map;

/**
 * 事件三步曲: {
 *      1->定义事件{@link RemoteAppEvent},
 *      2->发布事件{@link com.gupao.micro.service.spring.cloud.ds.client.controller.RemoteAppEventSenderController#sendAppCluster(String, Map)},
 *      3->监听事件{@link HttpRemoteAppEventListener}
 *      }
 * 事件定义
 * 事件传输类型 HTTP,RPC,MQ
 * @author HXS
 * @copyright
 * @since 2019-01-28
 */
public class RemoteAppEvent extends ApplicationEvent {

    private String type;
    /**
     * 应用名称
     */
    private final String appName;
    /**
     * 是否广播到集群
     */
    private final boolean isCluster;

    public RemoteAppEvent(Object source,String appName, boolean isCluster) {
        super(source);
        this.appName = appName;
        this.isCluster = isCluster;
    }
    public String getAppName() {
        return appName;
    }

}
