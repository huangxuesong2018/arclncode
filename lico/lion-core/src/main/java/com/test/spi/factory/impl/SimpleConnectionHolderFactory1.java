package com.test.spi.factory.impl;

import com.test.spi.ConnectionHolder;
import com.test.spi.factory.ConnectionHolderFactory;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-28
 */
public class SimpleConnectionHolderFactory1 implements ConnectionHolderFactory {
    @Override
    public ConnectionHolder get() {
        return new SimpleConnectionHolder1();
    }
}
