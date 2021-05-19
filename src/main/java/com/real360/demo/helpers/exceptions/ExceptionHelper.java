package com.real360.demo.helpers.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHelper {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHelper.class);

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        logger.error("Exception: ", ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
