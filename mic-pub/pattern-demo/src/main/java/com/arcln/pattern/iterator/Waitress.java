package com.arcln.pattern.iterator;

import com.arcln.pattern.iterator.aggregate.Menu;
import com.arcln.pattern.iterator.aggregate.impl.DinerMenu;
import com.arcln.pattern.iterator.aggregate.impl.PancakeHouseMenu;
import com.arcln.pattern.iterator.iterators.MyIterator;

import java.util.List;

/**
 * @author HXS
 * @copyright s
 * @since 2019-07-17
 */
public class Waitress {
    List<Menu> menuList;

    public Waitress( List<Menu> menuList) {
        this.menuList = menuList;
    }
    public void printMenu(){
        for (Menu menu:menuList) {
            MyIterator iterator = menu.createIterator();
            printMenu(iterator);
        }
    }

    public static void printMenu(MyIterator iterator){
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }
}
