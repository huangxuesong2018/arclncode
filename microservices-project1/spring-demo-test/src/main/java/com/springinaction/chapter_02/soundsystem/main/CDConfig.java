package com.springinaction.chapter_02.soundsystem.main;

import com.springinaction.chapter_02.soundsystem.bean.CDPlayer;
import com.springinaction.chapter_02.soundsystem.bean.CompactDisc;
import com.springinaction.chapter_02.soundsystem.bean.MediaPlayer;
import com.springinaction.chapter_02.soundsystem.bean.SgtPeppers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-20
 */
@Configuration
public class CDConfig {
    @Bean
    public CompactDisc randomBeatlesCD(){
        return new SgtPeppers();
    }
}
