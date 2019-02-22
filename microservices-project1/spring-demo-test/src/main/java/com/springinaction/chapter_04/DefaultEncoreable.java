package com.springinaction.chapter_04;

import org.springframework.stereotype.Component;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-22
 */
@Component
public class DefaultEncoreable implements Encoreable {
    @Override
    public String performEncore() {
        System.out.println("ddddddddddd");
        return null;
    }
}
