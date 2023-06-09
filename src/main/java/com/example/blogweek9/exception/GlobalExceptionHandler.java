package com.example.blogweek9.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistExceptions.class)
    public ResponseEntity<ErrorDetails> handleUserAlreadyExistsException(UserAlreadyExistExceptions ex,
                                                                         WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                webRequest.getDescription(false));
        return  new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorDetails> handleBlogApiException(BlogApiException ex,
                                                                         WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                webRequest.getDescription(false));
        return  new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex,
                                                               WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                webRequest.getDescription(false));
        return  new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
