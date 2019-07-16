package com.arcln.pattern.command.impl;

import com.arcln.pattern.command.Command;

/**
 *  Concrete Command
 * @author HXS
 * @copyright
 * @since 2019-07-04
 */
public class GarageDoorDownCommand implements Command {
    private GarageDoor garageDoor;

    public GarageDoorDownCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        this.garageDoor.down();
    }
}
