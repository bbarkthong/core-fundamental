package com.basah.spring.corefundamental.trace.hellotrace;

import org.junit.jupiter.api.Test;

import com.basah.spring.corefundamental.trace.TraceStatus;

public class HelloTraceV1Test {
    @Test
    void begin_end() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    void begin_exception() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalAccessException());
    }
}
