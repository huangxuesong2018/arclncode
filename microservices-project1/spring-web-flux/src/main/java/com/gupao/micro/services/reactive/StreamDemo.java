package com.gupao.micro.services.reactive;

import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        Stream.of(0,1,2,3,4,5,6,7,8,9)
                .filter(a->a%2==1)
                .map(a->a+1)
                .reduce(Integer::sum)
                .ifPresent(System.out::println);
                //.forEach(System.out::println);
    }
}
