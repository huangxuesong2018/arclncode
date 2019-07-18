package com.arcln.pattern.iterator.iterators.impl;

import com.arcln.pattern.iterator.MenuItem;
import com.arcln.pattern.iterator.aggregate.Menu;
import com.arcln.pattern.iterator.aggregate.impl.CoffeeMenu;
import com.arcln.pattern.iterator.iterators.MyIterator;

import java.util.*;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-18
 */
public class CoffeeMenuIterator implements MyIterator{
    Iterator<MenuItem> iterator;
    int index;

    public CoffeeMenuIterator(Map<String,MenuItem> map) {
        this.iterator = map.values().iterator();
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public MenuItem next() {
        return this.iterator.next();
    }
}
