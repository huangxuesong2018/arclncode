package com.test.spi;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-26
 */
public class JdbcConnection implements Connect {
    @Override
    public boolean isConnected() {
        return false;
    }
}
