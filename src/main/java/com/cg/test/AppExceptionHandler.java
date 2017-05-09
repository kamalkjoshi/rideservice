package com.cg.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cg.test.exception.CabNotFoundException;
import com.cg.test.exception.InvalidRequestException;

/**
 * Exception handler for preparing  error responses.
 */
@ControllerAdvice
public class AppExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server error")
	@ExceptionHandler(Exception.class)
	public void handleException(Exception ex) {
		logger.error("Error handling api:",ex);
	}

	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Request Validation failed")
	@ExceptionHandler(InvalidRequestException.class)
	public void handleValidationException(InvalidRequestException ex) {
		logger.error("Error handling api:",ex);
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not a valid resource")
	@ExceptionHandler(CabNotFoundException.class)
	public void handleCabNotFoundException(CabNotFoundException ex) {
		logger.error("No cab found: {}",ex.getMessage());
	}

}
