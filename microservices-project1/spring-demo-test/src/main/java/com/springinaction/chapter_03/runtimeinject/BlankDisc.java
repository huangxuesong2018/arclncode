package com.springinaction.chapter_03.runtimeinject;

import org.springframework.beans.factory.annotation.Value;

/**
 *  SpEL 表达式要放到 “#{ ... }” 之中，这与属性占位符有些类似，属性占位符需要放到 “${ ... }” 之中
 * @author HXS
 * @copyright
 * @since 2019-02-21
 */
public class BlankDisc {
    private String title;
    private String artist;

    //解析属性占位符
    @Value(value = "${disc.author}")
    public String author;
    /**
     * @param title
     * @param artist
     */
    public BlankDisc(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "BlankDisc{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
