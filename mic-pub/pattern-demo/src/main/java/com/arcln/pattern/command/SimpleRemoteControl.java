package com.arcln.pattern.command;

/**
 * 这个一个摇控器的插槽
 * @author HXS
 * @copyright
 * @since 2019-07-04
 */
public class SimpleRemoteControl {
    private Command slot;

    public void setCommand(Command command) {
        this.slot = command;
    }

    public void buttonWasPress(){
        this.slot.execute();
    }
}
