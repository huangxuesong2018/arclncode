package com.test.spi;

import com.test.spi.factory.ConnectionHolderFactory;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author HXS
 * @copyright
 * @since 2019-04-25
 */
public class Test2 {

    public static void main(String[] args) {
/*        ConnectionHolder holderFactory = ConnectionHolderFactory.create();
        holderFactory.getConnection();*/
        ServiceLoader<ConnectionHolderFactory> s = ServiceLoader.load(ConnectionHolderFactory.class);
        Iterator<ConnectionHolderFactory> it =  s.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }

    }

}
