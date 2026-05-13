package com.hospital.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseException extends RuntimeException{

	private final String errorCode;
	
	public BaseException(String message, String errorCode) {
		
		super(message);
		
		this.errorCode = errorCode;
	}
}
