package com.arcln.pattern.command;

/**
 *  Invoker
 * @author HXS
 * @copyright
 * @since 2019-07-05
 */
public class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;

    public RemoteControl() {
        this.onCommands = new Command[7];
        this.offCommands = new Command[7];
    }
}
