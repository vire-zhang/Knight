package com.springinaction.knights.impl;

import com.springinaction.knights.Knight;
import com.springinaction.knights.Quest;

public class BraveKnight implements Knight {

    private Quest quest;

    public BraveKnight(Quest quest) {
        System.out.println("BraveKnight的有参构造函数......");
        this.quest = quest;
    }

    @Override
    public void embarkOnQuest() {
        System.out.println("BraveKnight.embarkOnQuest()......");
        quest.embark();
    }
}
