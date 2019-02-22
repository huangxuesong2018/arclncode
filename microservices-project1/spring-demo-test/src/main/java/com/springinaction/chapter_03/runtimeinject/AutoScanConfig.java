package com.springinaction.chapter_03.runtimeinject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-21
 */
@Configuration
@ComponentScan
@PropertySource(value = "classpath:chapter_03/app.properties")//声明属性源
public class AutoScanConfig {
}
