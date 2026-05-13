package com.hospital.common.exception;

public class DuplicateResourceException
        extends BaseException {

    public DuplicateResourceException(String message) {

        super(message, "DUPLICATE_RESOURCE");
    }
}