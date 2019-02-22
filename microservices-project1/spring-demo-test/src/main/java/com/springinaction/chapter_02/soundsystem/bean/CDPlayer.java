package com.springinaction.chapter_02.soundsystem.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-19
 */
public class CDPlayer implements MediaPlayer {
    private CompactDisc compactDisc;

    /**
     * 构造注入 @Autowired 此处可省略
     * @param compactDisc
     */
    @Autowired
    public CDPlayer(CompactDisc compactDisc) {
        this.compactDisc = compactDisc;
    }

    @Override
    public void play() {
        System.out.println("com.springinaction.chapter_02.soundsystem.bean.CDPlayer.play()");
        compactDisc.play();
    }
}
