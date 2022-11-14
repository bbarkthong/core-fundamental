package com.basah.spring.corefundamental;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.basah.spring.corefundamental.trace.logtrace.LogTrace;
import com.basah.spring.corefundamental.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
