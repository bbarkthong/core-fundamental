package com.basah.spring.corefundamental.V1;

import org.springframework.stereotype.Service;

import com.basah.spring.corefundamental.trace.TraceStatus;
import com.basah.spring.corefundamental.trace.hellotrace.HelloTraceV1;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepositoryV1;
    private final HelloTraceV1 trace;

    public void orderItem(String ItemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = trace.begin("OrderService.orderItem()");
            orderRepositoryV1.save(ItemId);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }
    }
}
