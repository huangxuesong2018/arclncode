package myio.nettry.channel;

import io.netty.channel.SelectStrategyFactory;
import io.netty.util.NettyRuntime;
import io.netty.util.concurrent.RejectedExecutionHandler;
import io.netty.util.internal.SystemPropertyUtil;

import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.Executor; /**
 * @author HXS
 * @copyright
 * @since 2019-04-23
 */
public abstract class MyMultithreadEventLoopGroup  implements MyEventLoopGroup{
    private static final int DEFAULT_EVENT_LOOP_THREADS;
    static {
        DEFAULT_EVENT_LOOP_THREADS =  Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
    }
    public MyMultithreadEventLoopGroup(int nThreads, Executor executor, Object... args) {
       // super(nThreads == 0 ? DEFAULT_EVENT_LOOP_THREADS : nThreads, executor, args);
    }
}
