package com.arcln.pattern.Observer.impl2;

import java.util.Observable;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-27
 */
public class WeatherData extends Observable {
    public void measurementsChanged(float temp,float humidity,float pressure){
        Data data = new Data(temp, humidity, pressure);
        setChanged();
        notifyObservers(data);
    }
}
