package com.gupao.spring.cloud.feign.client.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * @author HXS
 * @copyright
 * @since 2019-01-02
 */
public class TwoServerForeverRule extends AbstractLoadBalancerRule {
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        ILoadBalancer iLoadBalancer = getLoadBalancer();
        List<Server> list =  iLoadBalancer.getAllServers();
        System.out.println("自定义负载规则2->服务列表："+list);
        return list.get(0);
    }
}
