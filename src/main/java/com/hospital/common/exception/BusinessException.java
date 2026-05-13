package com.hospital.common.exception;

public class BusinessException
        extends BaseException {

    public BusinessException(String message) {

        super(message, "BUSINESS_EXCEPTION");
    }
}