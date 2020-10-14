package com.concert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com.concert/concertConfig.xml")
public class MovieTest {

    @Autowired
    private Performance performance;

    @Test
    public void movieTest() {
        performance.perform();
    }
}
