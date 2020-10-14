package com.springication.knights;

import static org.mockito.Mockito.*;

import com.springinaction.knights.impl.BraveKnight;
import com.springinaction.knights.Quest;
import org.junit.Test;

public class BraveKnightTest {

    @Test
    public void knightShouldEmbarkOnQuest() {
        Quest mockQuest = mock(Quest.class);
        BraveKnight knight = new BraveKnight(mockQuest);
        knight.embarkOnQuest();
        verify(mockQuest, times(1)).embark();
    }
}
