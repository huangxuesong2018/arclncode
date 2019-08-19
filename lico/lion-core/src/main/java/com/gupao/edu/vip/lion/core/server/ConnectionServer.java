package com.gupao.edu.vip.lion.core.server;

import com.gupao.edu.vip.lion.api.connection.ConnectionManager;
import com.gupao.edu.vip.lion.core.LionServer;
import com.gupao.edu.vip.lion.network.netty.server.NettyTcpServer;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-24
 */
public final class ConnectionServer{ //extends NettyTcpServer{
    //Lion主服务
    private LionServer lionServer;
    private ConnectionManager connectionManager;

    public ConnectionServer(LionServer lionServer){
        this.lionServer = lionServer;
        this.connectionManager = new ServerConnectionManager(true);
    }
}
