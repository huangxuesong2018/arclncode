package com.arcln.pattern.iterator.aggregate.impl;

import com.arcln.pattern.iterator.aggregate.Menu;
import com.arcln.pattern.iterator.iterators.impl.DinerMenuIterator;
import com.arcln.pattern.iterator.MenuItem;
import com.arcln.pattern.iterator.iterators.MyIterator;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-17
 */
public class DinerMenu implements Menu {
    static final int MAX_ITEMS = 4;
    int numberOfItems = 0;
    MenuItem[] menuItems;
    public DinerMenu() {
        this.menuItems = new MenuItem[MAX_ITEMS];
        addItem("1111",
                "Pancakes with scrambled eggs",
                true,2.99);
        addItem("2222",
                "Pancakes with fried eggs,sausage",
                true,2.99);
        addItem("3333","Pancakes made with fresh blueberries",
                true,3.49);
        addItem("4444",
                "Waffles",
                true,3.59);
    }

    private void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name,description,vegetarian,price);
        if (numberOfItems >= 6){
            System.err.println("menu is full");
        }else {
            menuItems[numberOfItems] = menuItem;
            numberOfItems ++;
        }
    }

    @Override
    public MyIterator createIterator() {
        return new DinerMenuIterator(menuItems);
    }
}
