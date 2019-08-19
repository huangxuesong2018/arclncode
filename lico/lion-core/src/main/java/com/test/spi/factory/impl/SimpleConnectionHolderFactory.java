package com.test.spi.factory.impl;

import com.test.spi.ConnectionHolder;
import com.test.spi.factory.ConnectionHolderFactory;
import com.test.spi.spi.Spi;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-26
 */
@Spi(order = -1)
public class SimpleConnectionHolderFactory implements ConnectionHolderFactory{
    @Override
    public ConnectionHolder get() {
        return new SimpleConnectionHolder();
    }
}
