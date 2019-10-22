package com.minesweeper.devigetchallenge.controller;

import com.minesweeper.devigetchallenge.exceptions.ApiError;
import com.minesweeper.devigetchallenge.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ApiError> noHandlerFoundException(HttpServletRequest req, NoHandlerFoundException ex) {
		ApiError apiError = new ApiError("route_not_found", String.format("Route %s not found", req.getRequestURI()), HttpStatus.NOT_FOUND.value());
		LOG.error("Exception: ", ex);
		return ResponseEntity.status(apiError.getStatus())
				.body(apiError);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ApiError> handleValidationException(ValidationException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ApiError.newBuilder().message(ex.getMessage()).build());
	}

	@ExceptionHandler(RuntimeException.class)
	public HttpEntity<ApiError> handleRuntimeException(RuntimeException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ApiError.newBuilder().message(ex.getMessage()).build());
	}

}