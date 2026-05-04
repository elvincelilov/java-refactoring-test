package com.sap.refactoring.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<String> handleDuplicate(DuplicateEmailException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<String> handleInvalid(InvalidUserException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

}
