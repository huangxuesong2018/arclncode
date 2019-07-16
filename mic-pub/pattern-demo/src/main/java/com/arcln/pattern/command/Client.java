package com.arcln.pattern.command;


import com.arcln.pattern.command.impl.GarageDoor;
import com.arcln.pattern.command.impl.GarageDoorDownCommand;
import com.arcln.pattern.command.impl.Light;
import com.arcln.pattern.command.impl.LightOnCommand;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-03
 */
public class Client {
    public static void main(String[] args) {
        SimpleRemoteControl control = new SimpleRemoteControl();

        Light light = new Light();
        LightOnCommand onCommand = new LightOnCommand(light);
        control.setCommand(onCommand);
        control.buttonWasPress();

        GarageDoor garageDoor = new GarageDoor();
        GarageDoorDownCommand doorDownCommand = new GarageDoorDownCommand(garageDoor);
        control.setCommand(doorDownCommand);
        control.buttonWasPress();
    }
}
