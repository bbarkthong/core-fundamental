package com.basah.spring.corefundamental.V3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basah.spring.corefundamental.trace.TraceStatus;
import com.basah.spring.corefundamental.trace.logtrace.LogTrace;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderServiceV3;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    private String request(String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = trace.begin("OrderController.request()");
            orderServiceV3.orderItem(itemId);
            trace.end(traceStatus);
            return "ok";
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }
    }
}
