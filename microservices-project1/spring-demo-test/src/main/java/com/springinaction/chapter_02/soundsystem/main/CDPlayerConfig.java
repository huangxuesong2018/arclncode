package com.springinaction.chapter_02.soundsystem.main;

import com.springinaction.chapter_02.soundsystem.bean.CDPlayer;
import com.springinaction.chapter_02.soundsystem.bean.CompactDisc;
import com.springinaction.chapter_02.soundsystem.bean.MediaPlayer;
import com.springinaction.chapter_02.soundsystem.bean.SgtPeppers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-19
 */
@Configuration
/**
 * 启用组件扫描，默认是不开启的
 * 如果没有其他配置的话， @ComponentScan 默认会扫描与配置类相同的包(Spring Boot 默认扫描规则因此而来)
 */
/*
   @ComponentScan( //basePackages = {"com.springinaction.chapter_02.soundsystem.bean"},
                basePackageClasses = {CompactDisc.class})
*/
public class CDPlayerConfig {
    @Bean
    public MediaPlayer mediaPlayer(CompactDisc cd){
        return new CDPlayer(cd);
    }
}
