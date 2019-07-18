package com.arcln.pattern.iterator.aggregate.impl;

import com.arcln.pattern.iterator.MenuItem;
import com.arcln.pattern.iterator.aggregate.Menu;
import com.arcln.pattern.iterator.iterators.MyIterator;
import com.arcln.pattern.iterator.iterators.impl.PancakeHouseMenuIterator;

import java.util.ArrayList;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-17
 */
public class PancakeHouseMenu implements Menu {
    ArrayList<MenuItem> menuItems;

    public PancakeHouseMenu() {
        this.menuItems = new ArrayList();
        addItem("AAA",
                "Pancakes with scrambled eggs",
                true,2.99);
        addItem("BBB",
                "Pancakes with fried eggs,sausage",
                true,2.99);
        addItem("CCC","Pancakes made with fresh blueberries",
                true,3.49);
        addItem("DDD",
                "Waffles",
                true,3.59);
    }

    private void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name,description,vegetarian,price);
        menuItems.add(menuItem);
    }

    @Override
    public MyIterator createIterator() {
        return new PancakeHouseMenuIterator(menuItems);
    }

}
