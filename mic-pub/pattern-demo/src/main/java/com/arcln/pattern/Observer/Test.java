package com.arcln.pattern.Observer;

import com.arcln.pattern.Observer.impl.CurrentConditionDisplay;
import com.arcln.pattern.Observer.impl.StatisticsDisplay;
import com.arcln.pattern.Observer.impl.WeatherData;

/**
 * 观察者模式
 * @author HXS
 * @copyright
 * @since 2019-06-27
 */
public class Test {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay conditionDisplay = new CurrentConditionDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        weatherData.measurementsChanged(3,4,5);
        weatherData.measurementsChanged(32,42,52);
        weatherData.measurementsChanged(333,344,445);
    }
}
