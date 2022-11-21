package com.basah.spring.corefundamental.trace.strategy;

import org.junit.jupiter.api.Test;

import com.basah.spring.corefundamental.trace.strategy.code.strategy.ContextV1;
import com.basah.spring.corefundamental.trace.strategy.code.strategy.Strategy;
import com.basah.spring.corefundamental.trace.strategy.code.strategy.StrategyLogic1;
import com.basah.spring.corefundamental.trace.strategy.code.strategy.StrategyLogic2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV1Test {

    public ContextV1Test() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        log.info("비즈니스로직 1 실행");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        log.info("비즈니스로직 2  실행");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    @Test
    void strategyV1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
    }

}
