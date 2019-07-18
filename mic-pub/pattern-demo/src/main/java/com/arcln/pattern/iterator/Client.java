package com.arcln.pattern.iterator;

import com.arcln.pattern.iterator.aggregate.Menu;
import com.arcln.pattern.iterator.aggregate.impl.CoffeeMenu;
import com.arcln.pattern.iterator.aggregate.impl.DinerMenu;
import com.arcln.pattern.iterator.aggregate.impl.PancakeHouseMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-17
 */
public class Client {
    public static void main(String[] args) {
        //处理数组的循环
        List<Menu> list = new ArrayList<>();
/*        list.add(new DinerMenu());
        list.add(new PancakeHouseMenu());*/
        list.add(new CoffeeMenu());
        Waitress waitress = new Waitress(list);
        waitress.printMenu();

    }

}
