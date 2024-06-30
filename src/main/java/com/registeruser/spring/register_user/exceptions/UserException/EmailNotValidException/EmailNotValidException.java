package com.registeruser.spring.register_user.exceptions.UserException.EmailNotValidException;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailNotValidException extends RuntimeException{
    public EmailNotValidException(String message) {
        super(message);
    }
}
