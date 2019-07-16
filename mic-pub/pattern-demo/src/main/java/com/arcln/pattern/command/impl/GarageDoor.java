package com.arcln.pattern.command.impl;

/**
 * Receiver
 * @author HXS
 * @copyright
 * @since 2019-07-04
 */
public class GarageDoor {
    public void up(){
        System.out.println("garageDoor open");
    }
    public void down(){
        System.out.println("garageDoor down");
    }
    public void stop(){
        System.out.println("garageDoor stop");
    }

}
