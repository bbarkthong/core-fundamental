package com.basah.spring.corefundamental.V2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basah.spring.corefundamental.trace.TraceStatus;
import com.basah.spring.corefundamental.trace.hellotrace.HelloTraceV2;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderServiceV2;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    private String request(String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = trace.begin("OrderController.request()");
            orderServiceV2.orderItem(traceStatus.getTraceId(), itemId);
            trace.end(traceStatus);
            return "ok";
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }
    }
}
