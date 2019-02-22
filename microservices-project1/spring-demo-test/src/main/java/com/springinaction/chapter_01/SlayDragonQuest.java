package com.springinaction.chapter_01;

import java.io.PrintStream;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-18
 */
public class SlayDragonQuest implements Quest {
    private PrintStream stream;

    public SlayDragonQuest(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void embark() {
        this.stream.println("杀死一只怪龙");
    }
}
