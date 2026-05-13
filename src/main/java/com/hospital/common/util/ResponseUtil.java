package com.hospital.common.util;

import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.MDC;

import com.hospital.common.response.ApiResponse;

public class ResponseUtil {

	private ResponseUtil() {}
	
	public static <T> ApiResponse<T> success(String message, T data){
		
		return ApiResponse.<T>builder()
				.success(true)
				.message(message)
				.data(data)
				.traceId(MDC.get("traceId"))
				.timestamp(LocalDateTime.now())
				.build();
	}
	
	public static <T> ApiResponse<T> failure(String message, Map<String, String> errors){
		
		return ApiResponse.<T>builder()
				.success(false)
				.message(message)
				.errors(errors)
				.traceId(MDC.get("traceId"))
				.timestamp(LocalDateTime.now())
				.build();
	}
}
