package com.arcln.pattern.template.demo;


/**
 * @author HXS
 * @copyright
 * @since 2019-07-16
 */
public class Person implements Comparable{
    private String name;
    private int height;

    public Person(String name, int height) {
        this.name = name;
        this.height = height;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", height=" + height +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return ((Person)o).height - this.height;
    }
}
