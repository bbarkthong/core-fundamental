package com.basah.spring.corefundamental.trace.logtrace;

import com.basah.spring.corefundamental.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
