package com.arcln.pattern.Observer.impl2;

/**
 * @author HXS
 * @copyright
 * @since 2019-06-27
 */
public class Test {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay();
        weatherData.addObserver(currentConditionDisplay);

        weatherData.measurementsChanged(3,4,5);
    }
}
