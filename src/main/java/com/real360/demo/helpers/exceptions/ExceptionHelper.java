package com.real360.demo.helpers.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHelper {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHelper.class);

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        logger.error("Exception: ", ex.getMessage());
        Map<String, String> response = new HashMap();
        response.put("en",  ex.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {FileNotFoundException.class})
    public ResponseEntity<Object> handleFileNotFoundException(FileNotFoundException ex) {
        logger.error("Exception: ", ex.getMessage());
        Map<String, String> response = new HashMap();
        response.put("en",  ex.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
    }
}
