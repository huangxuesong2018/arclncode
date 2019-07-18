package com.arcln.pattern.iterator.aggregate.impl;

import com.arcln.pattern.iterator.MenuItem;
import com.arcln.pattern.iterator.aggregate.Menu;
import com.arcln.pattern.iterator.iterators.MyIterator;
import com.arcln.pattern.iterator.iterators.impl.CoffeeMenuIterator;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-18
 */
public class CoffeeMenu implements Menu{
    Map<String,MenuItem> map = new HashMap<>();

    public CoffeeMenu() {
        addItem("aa11",
                "Pancakes with scrambled eggs",
                true,2.99);
        addItem("bb22",
                "Pancakes with fried eggs,sausage",
                true,2.99);
        addItem("cc33","Pancakes made with fresh blueberries",
                true,3.49);
        addItem("dd44",
                "Waffles",
                true,3.59);
    }

    private void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name,description,vegetarian,price);
        map.put(name,menuItem);
    }

    @Override
    public MyIterator createIterator() {
        return new CoffeeMenuIterator(map);
    }

}
