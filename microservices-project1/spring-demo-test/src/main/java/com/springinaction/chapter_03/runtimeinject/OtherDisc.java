package com.springinaction.chapter_03.runtimeinject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-21
 */
@Component
public class OtherDisc {
    private String title;
    private String artist;
    public String author;
  //  public OtherDisc(){}

    public OtherDisc(@Value("#{environment.getProperty('disc.artist')}") String title,
                     @Value("#{systemProperties['disc.artist']}") String artist,
                     @Value("#{systemProperties['disc.author']}") String author) {
        this.title = title;
        this.artist = artist;
        this.author = author;
    }

    @Override
    public String toString() {
        return "OtherDisc{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
