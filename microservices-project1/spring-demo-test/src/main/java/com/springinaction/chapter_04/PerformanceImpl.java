package com.springinaction.chapter_04;

import org.springframework.stereotype.Component;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-22
 */
@Component
public class PerformanceImpl implements Performance {
    @Override
    public void perform() {
        //表演失败
        //throw new IllegalArgumentException();

        System.out.println("表演中......");
    }

    @Override
    public void perform(String title) {
        System.out.println("表演中......"+title);
    }
}
