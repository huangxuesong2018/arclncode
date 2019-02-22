package com.springinaction.chapter_02.soundsystem.bean;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-19
 */
@Component
public class SgtPeppers implements CompactDisc,BeanNameAware{
    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";

    @Override
    public void play() {
        System.out.println("com.springinaction.chapter_02.soundsystem.SgtPeppers.play()");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("com.springinaction.chapter_02.soundsystem.SgtPeppers->beanName:"+name);
    }
}
