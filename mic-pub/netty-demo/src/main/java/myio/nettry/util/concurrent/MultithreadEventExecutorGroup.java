package myio.nettry.util.concurrent;

import io.netty.util.concurrent.DefaultEventExecutorChooserFactory;

import java.util.concurrent.Executor;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-23
 */
public class MultithreadEventExecutorGroup extends AbstractEventExecutorGroup{
    protected MultithreadEventExecutorGroup(int nThreads, Executor executor, Object... args) {
       // this(nThreads, executor, DefaultEventExecutorChooserFactory.INSTANCE, args);
    }
}
