package com.gupao.edu.vip.lion.network.netty.connection;

import com.gupao.edu.vip.lion.api.connection.Connection;
import com.gupao.edu.vip.lion.api.connection.SessionContext;
import com.gupao.edu.vip.lion.api.protocol.Packet;
import com.gupao.edu.vip.lion.api.spi.core.RsaCipherFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * 具体的连接
 * @copyright
 * @since 2019-04-25
 */
public class NettyConnection implements Connection,ChannelFutureListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyConnection.class);
    private Channel channel;
    private SessionContext context;
    private long lastWriteTime;
    private long lastReadTime;
    private volatile byte status = STATUS_NEW;

    @Override
    public void init(Channel channel, boolean security) {
        this.channel = channel;
        this.context = new SessionContext();
        this.lastReadTime = System.currentTimeMillis();
        this.status = STATUS_CONNECTED;
        if (security){
            this.context.changeCipher(RsaCipherFactory.create());
        }
    }


    @Override
    public SessionContext getSessionContext() {
        return null;
    }

    @Override
    public void setSessionContext(SessionContext context) {

    }

    @Override
    public ChannelFuture send(Packet packet) {
        return send(packet,null);
    }

    @Override
    public ChannelFuture send(Packet packet, ChannelFutureListener listener) {
        if (channel.isActive()){
            ChannelFuture future = channel.writeAndFlush(packet);

            if (Objects.nonNull(listener)){
                future.addListener(listener);
            }
            if (channel.isWritable()){
                return future;
            }

            if (!future.channel().eventLoop().inEventLoop()){
                future.awaitUninterruptibly(200);
            }
            return future;
        }else {
            this.close();
        }

        return null;
    }

    @Override
    public String getId() {
        return channel.id().asShortText();
    }

    @Override
    public ChannelFuture close() {
        if (status == STATUS_CONNECTED)
            return null;
        this.status = STATUS_CONNECTED;
        return this.channel.close();
    }

    @Override
    public boolean isConnected() {
        return status == STATUS_CONNECTED;
    }

    @Override
    public boolean isReadTimeout() {
        return System.currentTimeMillis() - lastReadTime > context.heartbeat + 1000;
    }

    @Override
    public boolean isWriteTimeout() {
        return System.currentTimeMillis() - lastWriteTime > context.heartbeat - 1000;
    }

    @Override
    public void updateLastReadTime() {
        lastReadTime = System.currentTimeMillis();
    }

    @Override
    public void updateLastWriteTime() {
        lastWriteTime = System.currentTimeMillis();
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        if (channelFuture.isSuccess()){
            lastWriteTime = System.currentTimeMillis();
        }else {
            LOGGER.error("连接 发送消息错误",channelFuture.cause());
        }
    }
}
