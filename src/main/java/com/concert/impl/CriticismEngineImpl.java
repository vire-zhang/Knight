package com.concert.impl;

import com.concert.CriticismEngine;

public class CriticismEngineImpl implements CriticismEngine {

    public CriticismEngineImpl() {
        super();
    }

    private String[] criticismPool;

    public void setCriticismPool(String[] criticismPool) {
        this.criticismPool = criticismPool;
    }

    @Override
    public String getCriticism() {
        int i = (int) (Math.random() * criticismPool.length);
        return criticismPool[i];
    }
}
