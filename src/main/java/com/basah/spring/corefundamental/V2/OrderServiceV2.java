package com.basah.spring.corefundamental.V2;

import org.springframework.stereotype.Service;

import com.basah.spring.corefundamental.trace.TraceId;
import com.basah.spring.corefundamental.trace.TraceStatus;
import com.basah.spring.corefundamental.trace.hellotrace.HelloTraceV2;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepositoryV2;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = trace.beginSync(traceId, "OrderService.orderItem()");
            orderRepositoryV2.save(traceStatus.getTraceId(), itemId);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }
    }

}
