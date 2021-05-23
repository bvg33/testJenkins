package com.epam.ems.controllers;

import com.epam.ems.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        String details = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse("error code =" + INTERNAL_SERVER_ERROR, details);
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
    }
}
