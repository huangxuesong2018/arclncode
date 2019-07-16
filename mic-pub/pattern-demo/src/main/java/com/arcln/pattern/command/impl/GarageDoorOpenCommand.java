package com.arcln.pattern.command.impl;

import com.arcln.pattern.command.Command;

/**
 *  Concrete Command
 * @author HXS
 * @copyright
 * @since 2019-07-04
 */
public class GarageDoorOpenCommand implements Command {
    private GarageDoor garageDoor;

    public GarageDoorOpenCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.up();
    }
}
