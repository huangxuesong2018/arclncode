package com.arcln.pattern.Observer.impl2;

import com.arcln.pattern.Observer.DisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-27
 */
public class StatisticsDisplay implements Observer,DisplayElement {
    private Data data;
    @Override
    public void display() {
        System.out.println(data);
    }

    @Override
    public void update(Observable o, Object arg) {
        data = (Data)arg;
        display();
    }
}
