package com.example.blogweek9.exception;

public class UserAlreadyExistExceptions extends RuntimeException{
    public UserAlreadyExistExceptions(String message){
        super(message);
    }
}
