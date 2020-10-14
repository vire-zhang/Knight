package com.yuanfudao.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public aspect Timer {

    private long startTime;

    private long endTime;

    public Timer() { }

    pointcut decode() : execution(* decodeString(..));

    before() : decode() {
        startTime = System.nanoTime();
    }

    after() : decode() {
        endTime = System.nanoTime();
        System.out.println("运行时间：" + (endTime - startTime) + "ns");
    }
}
