package com.example.freelance_resource_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.freelance_resource_backend.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {
	@ExceptionHandler({ResourceNotFoundException.class})
	ResponseEntity<String> handleBusinessException_NotFound(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({IllegalArgumentException.class})
	ResponseEntity<String> handleBusinessException_BadRequest(Exception ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
