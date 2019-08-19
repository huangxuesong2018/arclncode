package com.gupao.edu.vip.lion.api.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author HXS
 * @copyright
 * @since 2019-05-05
 */
public abstract class BaseService implements Service {
    protected final AtomicBoolean started = new AtomicBoolean();

    @Override
    public void init() {

    }

    @Override
    public boolean isRunning() {
        return started.get();
    }
}
