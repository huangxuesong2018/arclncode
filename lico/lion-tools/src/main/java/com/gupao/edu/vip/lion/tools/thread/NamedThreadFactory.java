package com.gupao.edu.vip.lion.tools.thread;

import java.util.concurrent.ThreadFactory;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-30
 */
public class NamedThreadFactory implements ThreadFactory {
    private final String namePrefix;
    private final ThreadGroup group;
    public NamedThreadFactory(final String namePrefix) {
        this.namePrefix = namePrefix;
        this.group = Thread.currentThread().getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        return newThread("none",r);
    }
    public Thread newThread(String name, Runnable r) {
        Thread thread = new Thread(r,namePrefix+""+name);
        return null;

    }

}
