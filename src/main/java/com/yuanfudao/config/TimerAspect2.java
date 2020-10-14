package com.yuanfudao.config;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class TimerAspect2 {

    private long startTime;

    private long endTime;

//    @Pointcut("execution(* com.yuanfudao.DecodeString.decodeString(..))")
    public void decode() {}

//    @Before("decode()")
    public void timerBefore() {
        startTime = System.nanoTime();
    }

//    @AfterReturning("decode()")
    public void timerAfter() {
        endTime = System.nanoTime();
        System.out.println("运行时间：" + (endTime - startTime) + "ns");
    }
}
