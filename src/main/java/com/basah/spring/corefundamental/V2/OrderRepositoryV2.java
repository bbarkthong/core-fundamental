package com.basah.spring.corefundamental.V2;

import org.springframework.stereotype.Repository;

import com.basah.spring.corefundamental.trace.TraceId;
import com.basah.spring.corefundamental.trace.TraceStatus;
import com.basah.spring.corefundamental.trace.hellotrace.HelloTraceV2;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = trace.beginSync(traceId, "OrderRepository.save()");
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
