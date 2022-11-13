package com.basah.spring.corefundamental.V3;

import org.springframework.stereotype.Service;

import com.basah.spring.corefundamental.trace.TraceStatus;
import com.basah.spring.corefundamental.trace.logtrace.LogTrace;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepositoryV3;
    private final LogTrace trace;

    public void orderItem(String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = trace.begin("OrderService.orderItem()");
            orderRepositoryV3.save(itemId);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }
    }

}
