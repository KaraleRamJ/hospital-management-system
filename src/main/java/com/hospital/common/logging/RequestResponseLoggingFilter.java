package com.hospital.common.logging;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.MDC;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
public class RequestResponseLoggingFilter
        extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(

            HttpServletRequest request,

            HttpServletResponse response,

            FilterChain filterChain)

            throws ServletException,
            IOException {

        long startTime =
                System.currentTimeMillis();

        try {

            filterChain.doFilter(
                    request,
                    response
            );

        } finally {

            long duration =
                    System.currentTimeMillis()
                            - startTime;

            log.info(

                    "Request completed. method={}, uri={}, status={}, duration={}ms, traceId={}",

                    request.getMethod(),

                    request.getRequestURI(),

                    response.getStatus(),

                    duration,

                    MDC.get("traceId")
            );
        }
    }
}