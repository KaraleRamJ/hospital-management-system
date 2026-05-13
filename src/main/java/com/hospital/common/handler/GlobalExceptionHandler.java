package com.hospital.common.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hospital.common.exception.BusinessException;
import com.hospital.common.exception.DuplicateResourceException;
import com.hospital.common.exception.ResourceNotFoundException;
import com.hospital.common.exception.UnauthorizedException;
import com.hospital.common.response.ApiResponse;
import com.hospital.common.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

	@Slf4j
	@RestControllerAdvice
	public class GlobalExceptionHandler {

	    @ExceptionHandler(
	            ResourceNotFoundException.class
	    )
	    public ResponseEntity<ApiResponse<Object>>
	    handleNotFound(
	            ResourceNotFoundException ex) {

	        log.warn(
	                "Resource not found. traceId={}, message={}",
	                MDC.get("traceId"),
	                ex.getMessage()
	        );

	        return ResponseEntity

	                .status(HttpStatus.NOT_FOUND)

	                .body(
	                        ResponseUtil.failure(
	                                ex.getMessage(),
	                                null
	                        )
	                );
	    }

	    @ExceptionHandler(
	            DuplicateResourceException.class
	    )
	    public ResponseEntity<ApiResponse<Object>>
	    handleDuplicate(
	            DuplicateResourceException ex) {

	        log.warn(
	                "Duplicate resource. traceId={}, message={}",
	                MDC.get("traceId"),
	                ex.getMessage()
	        );

	        return ResponseEntity

	                .status(HttpStatus.CONFLICT)

	                .body(
	                        ResponseUtil.failure(
	                                ex.getMessage(),
	                                null
	                        )
	                );
	    }

	    @ExceptionHandler(
	            UnauthorizedException.class
	    )
	    public ResponseEntity<ApiResponse<Object>>
	    handleUnauthorized(
	            UnauthorizedException ex) {

	        return ResponseEntity

	                .status(HttpStatus.UNAUTHORIZED)

	                .body(
	                        ResponseUtil.failure(
	                                ex.getMessage(),
	                                null
	                        )
	                );
	    }

	    @ExceptionHandler(
	            BusinessException.class
	    )
	    public ResponseEntity<ApiResponse<Object>>
	    handleBusiness(
	            BusinessException ex) {

	        return ResponseEntity

	                .badRequest()

	                .body(
	                        ResponseUtil.failure(
	                                ex.getMessage(),
	                                null
	                        )
	                );
	    }

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ApiResponse<Object>>
	    handleValidation(MethodArgumentNotValidException ex) {

	        Map<String, String> errors = new HashMap<>();

	        ex.getBindingResult()

	                .getAllErrors()

	                .forEach(error -> {

	                    String fieldName =
	                            ((FieldError) error)
	                                    .getField();

	                    String errorMessage =
	                            error.getDefaultMessage();

	                    errors.put(
	                            fieldName,
	                            errorMessage
	                    );
	                });

	        log.warn(
	                "Validation failed. traceId={}, errors={}",
	                MDC.get("traceId"),
	                errors
	        );

	        return ResponseEntity

	                .badRequest()

	                .body(
	                        ResponseUtil.failure(
	                                "Validation failed",
	                                errors
	                        )
	                );
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ApiResponse<Object>>
	    handleGlobal(Exception ex) {

	        log.error(
	                "Unhandled exception. traceId={}",
	                MDC.get("traceId"),
	                ex
	        );

	        return ResponseEntity

	                .internalServerError()

	                .body(
	                        ResponseUtil.failure(
	                                "Internal Server Error",
	                                null
	                        )
	                );
	    }
	
}
