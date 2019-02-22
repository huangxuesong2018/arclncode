package com.springinaction.chapter_03.condition;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-20
 */
public class MagicBean {
    private String type;

    public MagicBean(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "MagicBean{" +
                "type='" + type + '\'' +
                '}';
    }
}
