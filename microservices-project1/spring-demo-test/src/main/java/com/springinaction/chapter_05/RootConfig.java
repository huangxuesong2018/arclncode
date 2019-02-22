package com.springinaction.chapter_05;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-22
 */
@Configuration
@ComponentScan(excludeFilters = {@Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)})
public class RootConfig {
}
