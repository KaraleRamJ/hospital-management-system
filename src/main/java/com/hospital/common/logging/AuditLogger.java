package com.hospital.common.logging;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.MDC;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuditLogger {

    public void logAction(

            String action,

            String username,

            String entity,

            Long entityId) {

        log.info(

                "AUDIT action={}, user={}, entity={}, entityId={}, traceId={}",

                action,

                username,

                entity,

                entityId,

                MDC.get("traceId")
        );
    }
}