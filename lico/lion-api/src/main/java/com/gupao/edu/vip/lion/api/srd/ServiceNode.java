package com.gupao.edu.vip.lion.api.srd;

/**
 * 服务节点
 * @author HXS
 * @copyright
 * @since 2019-05-10
 */
public interface ServiceNode {
    /**
     * 服务名称
     * @return
     */
    String serviceName();

    /**
     * 节点id
     * @return
     */
    String nodeId();

    String getHost();

    int getPort();

    default <T> T getAttr(String name){
        return null;
    }

    default boolean isPersistent() {
        return false;
    }

    default String hostAndPort() {
        return getHost() + ":" + getPort();
    }

    default String getPath(){
        return serviceName()+ "/" +nodeId();
    }
}
