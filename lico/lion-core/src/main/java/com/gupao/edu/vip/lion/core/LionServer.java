package com.gupao.edu.vip.lion.core;

import com.gupao.edu.vip.lion.api.srd.ServiceNode;
import com.gupao.edu.vip.lion.common.ServerNodes;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-24
 */
public final class LionServer {
    private ServiceNode connServerNode;

    public LionServer(){
        connServerNode = ServerNodes.cs();
    }

    public ServiceNode getConnServerNode() {
        return connServerNode;
    }
}
