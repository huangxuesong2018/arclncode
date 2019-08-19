package com.test.spi.factory.impl;

import com.test.spi.Connect;
import com.test.spi.ConnectionHolder;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-25
 */
public class SimpleConnectionHolder implements ConnectionHolder {

    @Override
    public Connect getConnection() {
        System.out.println("SimpleConnectionHolder----------");
        return null;
    }
}
