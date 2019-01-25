package com.gupao.micro.services.mvc.reactive;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author HXS
 * @copyright
 * @since 2019-01-24
 */
public class ReactorDemo {
    public static void main(String[] args) {
        String[] ary = {"e","a","b","b","c","","e"};
        List<String> list = Arrays.asList(ary);
        List<String> r = list.stream() //为集合创建串行流。
            .map(key->key+key)//修改元素
            .filter(string->!string.isEmpty()) //过滤
            .distinct()//去重
            .limit(5)//输出限制数量
            .sorted()//排序
            .collect(Collectors.toList());
        System.out.println(r);


    }
}
