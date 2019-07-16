package com.gupao.edu.vip.lion.common;

import com.gupao.edu.vip.lion.api.srd.CommonServiceNode;
import com.gupao.edu.vip.lion.api.srd.ServiceNode;
import com.gupao.edu.vip.lion.tools.config.CC;
import com.gupao.edu.vip.lion.tools.config.ConfigTools;

/**
 * @author HXS
 * @copyright
 * @since 2019-05-10
 */
public class ServerNodes {
    public static ServiceNode cs() {
        CommonServiceNode node = new CommonServiceNode();
        node.setHost(ConfigTools.getConnectServerRegisterIp());
        node.setPort(CC.lion.net.connect_server_port);
        node.setPersistent(false);
        node.setServiceName(ServiceNames.CONN_SERVER);
        node.setAttrs(CC.lion.net.connect_server_register_attr);
        return node;
    }
}
