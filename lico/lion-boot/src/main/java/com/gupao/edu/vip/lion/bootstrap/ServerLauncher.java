package com.gupao.edu.vip.lion.bootstrap;

import com.gupao.edu.vip.lion.core.LionServer;

/**
 * 服务发射器
 * @author HXS
 * @copyright
 * @since 2019-05-10
 */
public final class ServerLauncher {
    private LionServer lionServer;
    public void init() {
        if (lionServer == null) {
            lionServer = new LionServer();
        }
    }
}
