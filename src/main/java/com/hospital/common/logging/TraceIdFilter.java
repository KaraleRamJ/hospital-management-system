package com.hospital.common.logging;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.MDC;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TraceIdFilter
        implements Filter {

    private static final String TRACE_ID =
            "traceId";

    @Override
    public void doFilter(

            ServletRequest request,

            ServletResponse response,

            FilterChain chain)

            throws IOException,
            ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        String traceId =
                UUID.randomUUID().toString();

        try {

            MDC.put(
                    TRACE_ID,
                    traceId
            );

            log.info(
                    "Incoming request. method={}, uri={}, traceId={}",

                    httpRequest.getMethod(),

                    httpRequest.getRequestURI(),

                    traceId
            );

            chain.doFilter(request, response);

        } finally {

            MDC.clear();
        }
    }
}