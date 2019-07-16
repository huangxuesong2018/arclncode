package com.arcln.pattern.Observer.impl;

import com.arcln.pattern.Observer.Observer;
import com.arcln.pattern.Observer.Subject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-27
 */
public class WeatherData implements Subject {
    private Set<Observer> observerList;
    private float temp;
    private float humidity;
    private float pressure;

    public WeatherData() {
        this.observerList = new HashSet<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Iterator<Observer> it = observerList.iterator();it.hasNext(); ) {
            it.next().update(temp,humidity,pressure);
        }
    }

    public void measurementsChanged(float temp,float humidity,float pressure){
        this.temp = temp;
        this.humidity = humidity;
        this.pressure =pressure;
        notifyObservers();
    }
}
