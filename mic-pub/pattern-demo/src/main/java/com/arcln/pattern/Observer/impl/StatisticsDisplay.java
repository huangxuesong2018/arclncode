package com.arcln.pattern.Observer.impl;

import com.arcln.pattern.Observer.DisplayElement;
import com.arcln.pattern.Observer.Observer;
import com.arcln.pattern.Observer.Subject;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-27
 */
public class StatisticsDisplay implements Observer,DisplayElement {
    private float humidity;
    private float pressure;
    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public String toString() {
        return "StatisticsDisplay{" +
                "humidity=" + humidity +
                ", pressure=" + pressure +
                '}';
    }
}
