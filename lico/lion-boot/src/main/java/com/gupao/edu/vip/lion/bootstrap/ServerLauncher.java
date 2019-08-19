package com.gupao.edu.vip.lion.bootstrap;

import com.gupao.edu.vip.lion.api.com.gupao.edu.vip.lion.api.common.ServerEventListener;
import com.gupao.edu.vip.lion.api.spi.core.ServerEventListenerFactory;
import com.gupao.edu.vip.lion.core.LionServer;

/**
 * 服务发射器
 * @author HXS
 * @copyright
 * @since 2019-05-10
 */
public final class ServerLauncher {
    private LionServer lionServer;
    private ServerEventListener serverEventListener;
    public void init() {
        if (lionServer == null) {
            lionServer = new LionServer();
        }

        if (serverEventListener == null) {
            serverEventListener = ServerEventListenerFactory.create();
        }
    }
}
