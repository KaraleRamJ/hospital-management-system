package com.hospital.common.exception;

public class UnauthorizedException
        extends BaseException {

    public UnauthorizedException(
            String message) {

        super(message, "UNAUTHORIZED");
    }
}