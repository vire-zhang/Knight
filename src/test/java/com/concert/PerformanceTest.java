package com.concert;

import com.concert.config.ConcertConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConcertConfig.class)
public class PerformanceTest {

    @Autowired
//    private AudienceAround audienceAround;
//    private Audience audience;

    @Test
    public void performanceTest() {
        Performance mockPerformance = mock(Performance.class);
        mockPerformance.perform();
        verify(mockPerformance, times(1)).perform();
//        audience.applause();
//        verify(audience, times(1)).applause();
    }
}
