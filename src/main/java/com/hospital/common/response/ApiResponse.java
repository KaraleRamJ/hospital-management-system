package com.hospital.common.response;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse <T>{

	private boolean success;
	
	private String message;
	
	private T data;
	
	private Map<String, String> errors;
	
	private String traceId;
	
	private LocalDateTime timestamp;
}
