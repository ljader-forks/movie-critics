package com.rys.moviecritics.rate.rest;

import com.rys.moviecritics.rate.exception.NotFoundResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NotFoundResourceException.class)
    ResponseEntity<String> handleNotFoundResourceException(final NotFoundResourceException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
