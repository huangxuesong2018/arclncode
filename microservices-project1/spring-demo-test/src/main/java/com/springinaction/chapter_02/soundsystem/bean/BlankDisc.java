package com.springinaction.chapter_02.soundsystem.bean;

import java.util.List;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-20
 */
public class BlankDisc implements CompactDisc {
    private String title ;
    private String artist ;
    private List<String> tracks;

    //属性注入时 需要一个 set方法
    private String name;

    /**
     * 构造方法注入
     * @param title
     * @param artist
     * @param tracks
     */
    public BlankDisc(String title, String artist, List<String> tracks) {
        this.title = title;
        this.artist = artist;
        this.tracks = tracks;
    }

    @Override
    public void play() {
        for (String track: this.tracks) {
            System.out.println(track);
        }
        System.out.println("name is:"+this.name);
        System.out.println("title is:"+this.title);
        System.out.println("artist is:"+this.artist);
    }

    public void setName(String name) {
        this.name = name;
    }
}
