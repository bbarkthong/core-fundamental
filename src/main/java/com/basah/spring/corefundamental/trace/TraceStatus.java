package com.basah.spring.corefundamental.trace;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TraceStatus {

    private TraceId traceId;
    private long startTimeMs;
    private String message;
}
