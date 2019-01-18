package com.gupao.micro.service.spring.cloud.servlet.gateway.loadbalancer;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author HXS
 * @copyright
 * @since 2019-01-17
 */
public class ZookeeperLoadBalancer extends BaseLoadBalancer{
    private final DiscoveryClient discoveryClient;

    private Map<String,BaseLoadBalancer> loadBalancerMap = new ConcurrentHashMap<>();

    public ZookeeperLoadBalancer(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        updateServers();
    }

    @Override
    public Server chooseServer(Object key) {
        if(key instanceof String){
            String serviceName = String.valueOf(key);
            BaseLoadBalancer baseLoadBalancer = loadBalancerMap.get(serviceName);
            //这个地方 拿不到东西......
            return baseLoadBalancer.chooseServer(serviceName);
        }
        return super.chooseServer(key);

    }

    @Scheduled(fixedDelay = 5000)
    private void updateServers() {
        //这个地方只会初始化一次，可以通过定时调度，和zookeeper的watcher
        discoveryClient.getServices().stream().forEach(serviceName -> {
            BaseLoadBalancer loadBalancer = new BaseLoadBalancer();
            loadBalancerMap.put(serviceName,loadBalancer);
            List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(serviceName);
            serviceInstanceList.forEach(serviceInstance -> {
                Server server = new Server(serviceInstance.isSecure() ? "https://" : "http://",
                        serviceInstance.getHost(),serviceInstance.getPort());
                loadBalancer.addServer(server);
            });

        });
    }
}
