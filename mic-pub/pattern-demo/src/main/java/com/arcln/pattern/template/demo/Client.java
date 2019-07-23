package com.arcln.pattern.template.demo;

import java.util.*;

/**
 * 模版方法模式
 * @author HXS
 * @copyright
 * @since 2019-07-16
 */
public class Client {
    public static void main(String[] args) {
        CaffeineBeverage drinks = new Coffee();


        Person[] ary = new Person[3];
        ary[0]= (new Person("张三",170));
        ary[1]= (new Person("李四",180));
        ary[2]= (new Person("王五",160));
        Arrays.sort(ary);
       for (Person person : ary){
           System.out.println(person);
       }
    }

}
