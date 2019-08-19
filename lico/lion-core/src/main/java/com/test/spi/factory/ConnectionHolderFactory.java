package com.test.spi.factory;

import com.test.spi.ConnectionHolder;
import com.test.spi.SpiLoader;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-25
 */
public interface ConnectionHolderFactory extends Factory<ConnectionHolder> {
    //具体是返回什么类型的 ConnectionHolder,看spi指定的实现类
    static ConnectionHolder create(){
        return SpiLoader.load(ConnectionHolderFactory.class).get();
    };
}
