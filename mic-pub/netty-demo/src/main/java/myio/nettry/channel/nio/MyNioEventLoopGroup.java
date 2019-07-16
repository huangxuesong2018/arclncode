package myio.nettry.channel.nio;

import io.netty.channel.DefaultSelectStrategyFactory;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SelectStrategyFactory;
import io.netty.util.concurrent.RejectedExecutionHandlers;
import myio.nettry.channel.MyEventLoopGroup;
import myio.nettry.channel.MyMultithreadEventLoopGroup;

import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.Executor;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-23
 */
public class MyNioEventLoopGroup extends MyMultithreadEventLoopGroup {
    public MyNioEventLoopGroup() {
        this(0);
    }

    public MyNioEventLoopGroup(int nThreads) {
        this(nThreads,(Executor) null);
    }
    public MyNioEventLoopGroup(int nThreads, Executor executor) {
        this(nThreads, executor, SelectorProvider.provider());
    }
    public MyNioEventLoopGroup(int nThreads, Executor executor,final SelectorProvider selectorProvider) {
        this(nThreads, executor, selectorProvider, DefaultSelectStrategyFactory.INSTANCE);
    }

    public MyNioEventLoopGroup(int nThreads, Executor executor, final SelectorProvider selectorProvider,
                             final SelectStrategyFactory selectStrategyFactory) {
        super(nThreads, executor, selectorProvider, selectStrategyFactory, RejectedExecutionHandlers.reject());
    }
}
