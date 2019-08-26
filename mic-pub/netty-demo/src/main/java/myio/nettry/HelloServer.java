package myio.nettry;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;
import myio.nettry.channel.MyEventLoopGroup;
import myio.nettry.channel.nio.MyNioEventLoopGroup;

/**
 * Netty源码
 * @author HXS
 * @copyright
 * @since 2019-04-22
 */
public class HelloServer {
    private static int port = 8888;

    public static void main(String[] args) {
        int i = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        System.out.println(i);
    }
}
