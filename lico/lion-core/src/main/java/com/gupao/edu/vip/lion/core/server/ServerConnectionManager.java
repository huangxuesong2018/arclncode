package com.gupao.edu.vip.lion.core.server;

import com.gupao.edu.vip.lion.api.connection.Connection;
import com.gupao.edu.vip.lion.api.connection.ConnectionManager;
import com.gupao.edu.vip.lion.network.netty.connection.NettyConnection;
import com.gupao.edu.vip.lion.tools.config.CC;
import com.gupao.edu.vip.lion.tools.thread.NamedThreadFactory;
import com.gupao.edu.vip.lion.tools.thread.ThreadNames;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author HXS
 * @copyright1217993
 *
 * @since 2019-04-24
 */
public final class ServerConnectionManager implements ConnectionManager{
    //是否需要心跳检测
    private final boolean heartbeatCheck;
    private final ConnectionHolderFactory holderFactory;
    private final ConcurrentHashMap<ChannelId,ConnectionHolder> connections = new ConcurrentHashMap<>();
    private HashedWheelTimer timer;
    public ServerConnectionManager(boolean heartbeatCheck) {
        this.heartbeatCheck = heartbeatCheck;
        this.holderFactory = heartbeatCheck ? HeartBeatCheckTask::new : SimpleConnectionHolder ::new;
    }


    private class HeartBeatCheckTask implements ServerConnectionManager.ConnectionHolder,TimerTask{
        private byte timeoutTimes = 0;//超时次数
        private Connection connection;

        private HeartBeatCheckTask(Connection connection) {
            this.connection = connection;
        }

        void startTimeout(){
            Connection connection = this.connection;
            if(Objects.nonNull(connection) && connection.isConnected()){
                int timeout = connection.getSessionContext().heartbeat;
            }
        }

        @Override
        public Connection get() {
            return connection;
        }

        @Override
        public void close() {
            if (Objects.nonNull(connection)){
                connection.close();
            }
        }

        @Override
        public void run(Timeout timeout) throws Exception {
            Connection connection = this.connection;
            if (Objects.nonNull(connection) && !connection.isConnected()){
                //todo add log
                return;
            }
            if (connection.isReadTimeout()){
                if (++timeoutTimes > CC.lion.core.max_hb_timeout_times){
                    connection.close();
                    //todo add log
                    return;
                }else {
                    //todo add log
                }
            }else {
                timeoutTimes = 0;
            }
            startTimeout();
        }
    }


    /**
     * 什么是函数式接口（Functional Interface）
     * 其实之前在讲Lambda表达式的时候提到过，所谓的函数式接口，当然首先是一个接口，然后就是在这个接口里面只能有一个抽象方法。
     这种类型的接口也称为SAM接口，即Single Abstract Method interfaces。
     */
    @FunctionalInterface
    private interface ConnectionHolderFactory{
        ServerConnectionManager.ConnectionHolder create(Connection connection);
    }

    private interface ConnectionHolder{
        Connection get();
        void close();
    }

    private static class SimpleConnectionHolder implements ServerConnectionManager.ConnectionHolder{
        private final Connection connection;

        private SimpleConnectionHolder(Connection connection) {
            this.connection = connection;
        }

        @Override
        public Connection get() {
            return connection;
        }

        @Override
        public void close() {
            if (Objects.nonNull(connection)){
                connection.close();
            }
        }
    }

    private final ConnectionHolder DEFAULT = new SimpleConnectionHolder(null);


    @Override
    public void add(Connection connection) {
        connections.putIfAbsent(connection.getChannel().id(),holderFactory.create(connection));
    }

    @Override
    public Connection get(Channel channel) {
        return connections.getOrDefault(channel.id(),DEFAULT).get();
    }

    @Override
    public Connection removeAndClose(Channel channel) {
        ConnectionHolder holder = connections.remove(channel.id());
        if (Objects.nonNull(holder)){
            Connection connection = holder.get();
            connection.close();
            return connection;
        }
        //add default
        Connection connection = new NettyConnection();
        connection.init(channel,false);
        connection.close();
        return connection;
    }


    @Override
    public int getConnNum() {
        return connections.size();
    }

    @Override
    public void init() {
        if (heartbeatCheck){
            long tickDuration = TimeUnit.SECONDS.toMicros(1);//每秒走一步,心跳周期走一圈
            int ticksPerWheel = (int)(CC.lion.core.max_heartbeat / tickDuration);
            this.timer = new HashedWheelTimer(
                    new NamedThreadFactory(ThreadNames.T_CONN_TIMER),
                    tickDuration, TimeUnit.MILLISECONDS, ticksPerWheel
            );
        }
    }

    @Override
    public void destroy() {

    }
}
