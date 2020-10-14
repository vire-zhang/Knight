package com.concert.aspect;

import com.concert.CriticismEngine;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 无法进行bean引用以及依赖注入
 * 原因未知
 */
public aspect CriticAspect {
    public CriticAspect() { }

    pointcut performance() : execution(* com.concert.Performance.perform(..));

    after() : com.concert.aspect.CriticAspect.performance() {
        System.out.println(criticismEngine.getCriticism());
    }

    private CriticismEngine criticismEngine;

    public void setCriticismEngine(CriticismEngine criticismEngine) {
        this.criticismEngine = criticismEngine;
    }
}
