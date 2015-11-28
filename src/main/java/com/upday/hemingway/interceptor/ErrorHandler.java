package com.upday.hemingway.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler
    void handleBadRequests(HttpServletResponse response, ConstraintViolationException e) throws Exception {
        logger.warn("Validation of input failed", e);
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Input data is invalid");
    }
}
