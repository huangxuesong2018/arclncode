package com.arcln.pattern.iterator.iterators.impl;

import com.arcln.pattern.iterator.MenuItem;
import com.arcln.pattern.iterator.iterators.MyIterator;

import java.util.ArrayList;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-17
 */
public class PancakeHouseMenuIterator implements MyIterator {
    ArrayList<MenuItem> menuItems;
    int index = 0;
    public PancakeHouseMenuIterator(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }


    @Override
    public boolean hasNext() {
        return index < menuItems.size();
    }

    @Override
    public MenuItem next() {
        return menuItems.get(index++);
    }
}
