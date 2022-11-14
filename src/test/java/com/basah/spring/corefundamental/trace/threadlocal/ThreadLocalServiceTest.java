package com.basah.spring.corefundamental.trace.threadlocal;

import org.junit.jupiter.api.Test;

import com.basah.spring.corefundamental.trace.threadlocal.code.ThreadLocalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService fieldService = new ThreadLocalService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> {
            fieldService.logic("userA");
        };
        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(200);
        threadB.start();

        sleep(3000);
        log.info("main exit");

    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
