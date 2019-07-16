package com.arcln.pattern.adapter.demo2;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-15
 */
public class EnumerationAdapter implements Iterator {
    private Enumeration e;

    public EnumerationAdapter(Enumeration e) {
        this.e = e;
    }

    @Override
    public boolean hasNext() {
        return e.hasMoreElements();
    }

    @Override
    public Object next() {
        return e.nextElement();
    }

}
