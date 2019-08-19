package com.gupao.edu.vip.lion.api.connection;

import com.gupao.edu.vip.lion.api.protocol.Packet;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-24
 */
public interface Connection {
    byte STATUS_NEW = 0;//0 连接创建了但并没有连接
    byte STATUS_CONNECTED = 1;//已经连接了
    byte STATUS_DISCONNECTED = 2;//关闭连接了

    void init(Channel channel, boolean security);

    SessionContext getSessionContext();

    void setSessionContext(SessionContext context);

    ChannelFuture send(Packet packet);

    ChannelFuture send(Packet packet, ChannelFutureListener listener);

    String getId();

    ChannelFuture close();

    boolean isConnected();

    boolean isReadTimeout();

    boolean isWriteTimeout();

    void updateLastReadTime();

    void updateLastWriteTime();

    Channel getChannel();
}
