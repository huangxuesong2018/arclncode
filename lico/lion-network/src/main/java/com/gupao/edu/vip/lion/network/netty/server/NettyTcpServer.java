package com.gupao.edu.vip.lion.network.netty.server;

import com.gupao.edu.vip.lion.api.service.BaseService;
import com.gupao.edu.vip.lion.api.service.Server;
import com.gupao.edu.vip.lion.api.service.ServiceException;
import io.netty.channel.EventLoopGroup;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author HXS
 * @copyright
 * @since 2019-05-05
 */
public abstract class NettyTcpServer extends BaseService implements Server {
    protected final int port;
    protected final String host;
    protected final AtomicReference<State> serverStatus = new AtomicReference<>(State.Created);
    public enum State{
        Created,Initialized,Starting,Started,Shutdown
    }
    protected EventLoopGroup bossGroup;
    protected EventLoopGroup workGroup;

    public NettyTcpServer(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public NettyTcpServer(int port) {
        this.port = port;
        this.host = null;
    }

    public void init(){
        if (!serverStatus.compareAndSet(State.Created,State.Initialized)){
            throw new ServiceException("Server already init");
        }
    }


}
