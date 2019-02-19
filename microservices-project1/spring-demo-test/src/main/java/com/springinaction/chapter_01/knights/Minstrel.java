package com.springinaction.chapter_01.knights;

import java.io.PrintStream;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-18
 */
public class Minstrel {
    private PrintStream stream;

    public Minstrel(PrintStream stream) {
        this.stream = stream;
    }

    public void singBeforeQuest() {
        stream.println("Fa la la, the knight is so brave!");
    }

    public void singAfterQuest() {
        stream.println("Tee hee hee, the brave knight " +
                "did embark on a quest!");
    }

}
