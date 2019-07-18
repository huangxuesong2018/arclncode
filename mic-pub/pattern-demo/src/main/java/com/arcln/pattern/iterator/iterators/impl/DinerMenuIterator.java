package com.arcln.pattern.iterator.iterators.impl;

import com.arcln.pattern.iterator.MenuItem;
import com.arcln.pattern.iterator.iterators.MyIterator;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-17
 */
public class DinerMenuIterator implements MyIterator {
    int index = 0;
    MenuItem[] menuItems;
    public DinerMenuIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        return index < menuItems.length;
    }

    @Override
    public MenuItem next() {
        return menuItems[index++];
    }
}
