package com.concert.impl;

import com.concert.Performance;
import org.springframework.stereotype.Component;

@Component
public class MoviePerform implements Performance {

    @Override
    public void perform() {
        System.out.println("Playing movie......");
    }
}
