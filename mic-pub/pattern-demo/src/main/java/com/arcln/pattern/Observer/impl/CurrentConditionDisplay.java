package com.arcln.pattern.Observer.impl;

import com.arcln.pattern.Observer.DisplayElement;
import com.arcln.pattern.Observer.Observer;
import com.arcln.pattern.Observer.Subject;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-27
 */
public class CurrentConditionDisplay implements Observer,DisplayElement {
    private float temp;
    private float humidity;
    private Subject weatherData;
    public CurrentConditionDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temp = temp;
        this.humidity = humidity;
        display();
    }

    @Override
    public String toString() {
        return "CurrentConditionDisplay{" +
                "temp=" + temp +
                ", humidity=" + humidity +
                '}';
    }
}
