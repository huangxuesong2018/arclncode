package com.gupao.edu.vip.nio.talk.entity;

import java.io.Serializable;

/**
 * @author HXS
 * @copyright
 * @since 2019-08-23
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 4426849368130355681L;
    private String name;
    private String message;

    public Message(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
