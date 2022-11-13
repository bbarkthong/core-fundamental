package com.basah.spring.corefundamental.trace.logtrace;

import com.basah.spring.corefundamental.trace.TraceId;
import com.basah.spring.corefundamental.trace.TraceStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLogTrace implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private TraceId traceIdHolder; // 동시성 이슈

    @Override
    public TraceStatus begin(String message) {

        syncTraceId();
        TraceId traceId = this.traceIdHolder;
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return TraceStatus.builder().startTimeMs(startTimeMs).traceId(traceId).message(message).build();
    }

    private void syncTraceId() {
        // if (traceIdHolder == null) {
        // traceIdHolder = new TraceId();
        // } else {
        // traceIdHolder = traceIdHolder.createNextId();
        // }
        traceIdHolder = (traceIdHolder == null) ? new TraceId() : traceIdHolder.createNextId();
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();

        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()),
                    status.getMessage(),
                    resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()),
                    status.getMessage(),
                    resultTimeMs, e.toString());
        }

        releaseTraceId();
    }

    private void releaseTraceId() {
        // if (traceIdHolder.isFirstLevel()) {
        // traceIdHolder = null;
        // } else {
        // traceIdHolder = traceIdHolder.createPrevId();
        // }
        traceIdHolder = (traceIdHolder.isFirstLevel()) ? null : traceIdHolder.createPrevId();
    }

    private static String addSpace(String prefix, int level) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|  ");
        }
        return sb.toString();
    }
}
