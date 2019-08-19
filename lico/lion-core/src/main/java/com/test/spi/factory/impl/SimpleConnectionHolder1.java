package com.test.spi.factory.impl;

import com.test.spi.Connect;
import com.test.spi.ConnectionHolder;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-28
 */
public class SimpleConnectionHolder1  implements ConnectionHolder {

    @Override
    public Connect getConnection() {
        System.out.println("SimpleConnectionHolder1----------");
        return null;
    }
}
