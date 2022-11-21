package com.basah.spring.corefundamental.V4;

import org.springframework.stereotype.Repository;

import com.basah.spring.corefundamental.trace.TraceStatus;
import com.basah.spring.corefundamental.trace.logtrace.LogTrace;
import com.basah.spring.corefundamental.trace.template.AbstractTemplate;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId) {

        AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
            @Override
            protected Void call() {
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("예외 발생!");
                }
                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepository.save()");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
