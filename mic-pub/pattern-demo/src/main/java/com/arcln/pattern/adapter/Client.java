package com.arcln.pattern.adapter;

import com.arcln.pattern.adapter.impl.TurKeyAdapter;
import com.arcln.pattern.adapter.impl.WildTurkey;

/**
 * 适配器
 * @author HXS
 * @copyright
 * @since 2019-07-15
 */
public class Client {
    public static void main(String[] args) {
        Duck duck = new TurKeyAdapter(new WildTurkey());
        duck.fly();
        duck.quack();
    }
}
