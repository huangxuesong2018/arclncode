package com.arcln.pattern.Observer;

/**
 * 主题
 * @author HXS
 * @copyright
 * @since 2019-06-27
 */
public interface Subject {
    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers();
}
