package com.springinaction.chapter_02.soundsystem.main;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-20
 */
@Configuration
@Import({CDPlayerConfig.class})
@ImportResource(locations = {"classpath:cd-config.xml"})
public class SoundSystemConfig {
}
