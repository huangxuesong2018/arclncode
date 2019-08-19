package com.gupao.edu.vip.lion.api.spi.core;

import com.gupao.edu.vip.lion.api.com.gupao.edu.vip.lion.api.common.ServerEventListener;
import com.gupao.edu.vip.lion.api.spi.Factory;
import com.gupao.edu.vip.lion.api.spi.SpiLoader;

/**
 * @author HXS
 * @copyright
 * @since 2019-05-10
 */
public interface ServerEventListenerFactory extends Factory<ServerEventListener> {
    static ServerEventListener create(){
        return SpiLoader.load(ServerEventListenerFactory.class).get();
    }

}
