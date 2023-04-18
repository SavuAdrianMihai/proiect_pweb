package com.example.demo.controller;

import com.example.demo.exceptions.NoListingsFound;
import com.example.demo.exceptions.NonexistentLegoSet;
import com.example.demo.exceptions.NonexistentListing;
import com.example.demo.exceptions.NonexistentUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NonexistentUser.class)
    public ResponseEntity handleNonexistentUser() {
        return new ResponseEntity("Nonexistent user", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NonexistentLegoSet.class)
    public ResponseEntity handleNonexistentLegoSet() {
        return new ResponseEntity("Nonexistent lego set", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NonexistentListing.class)
    public ResponseEntity handleNonexistentListing() {
        return new ResponseEntity("Nonexistent listing", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoListingsFound.class)
    public ResponseEntity handleNoListingsFound() {
        return new ResponseEntity("no listings found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UnknownError.class)
    public ResponseEntity handleUnknownError() {
        return new ResponseEntity("Something broke :(", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleDefaultException() {
        return new ResponseEntity("Default exception happened", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
