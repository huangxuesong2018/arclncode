package com.arcln.pattern.command.impl;

import com.arcln.pattern.command.Command;

/**
 * Concrete Command
 * @author HXS
 * @copyright
 * @since 2019-07-03
 */
public class LightOnCommand implements Command {
    private Light light;
    @Override
    public void execute() {
        light.on();
    }
    public LightOnCommand(Light light) {
        this.light = light;
    }

}
