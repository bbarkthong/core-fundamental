package com.basah.spring.corefundamental.V4;

import org.springframework.stereotype.Service;

import com.basah.spring.corefundamental.trace.TraceStatus;
import com.basah.spring.corefundamental.trace.logtrace.LogTrace;
import com.basah.spring.corefundamental.trace.template.AbstractTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepositoryV4;
    private final LogTrace trace;

    public void orderItem(String itemId) {

        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
            @Override
            protected String call() {
                orderRepositoryV4.save(itemId);
                return null;
            }
        };
        template.execute("OrderService.orderItem()");
    }

}
