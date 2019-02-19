package com.springinaction.chapter_01.knights;

/**
 * @author HXS
 * @copyright
 * @since 2019-02-18
 */
public class BraveKnight implements Knight {
    private Quest quest;

    public BraveKnight(Quest quest) {
        this.quest = quest;
    }

    public void embarkOnQuest(){
        quest.embark();
    }
}
