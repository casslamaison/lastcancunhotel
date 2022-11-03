package com.hotels.lastcancunhotel;

import java.util.Collections;
import java.util.HashSet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<Object> handleFeignExceptionUnauthorized(RuntimeException ex) {
		return ResponseEntity.badRequest().body(ExceptionResponse.builder()
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .details(new HashSet<>(Collections.singletonList(ex.getMessage())))
                .build());
	}
	
}
