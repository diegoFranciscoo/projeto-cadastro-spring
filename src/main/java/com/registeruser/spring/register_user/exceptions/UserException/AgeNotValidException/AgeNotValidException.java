package com.registeruser.spring.register_user.exceptions.UserException.AgeNotValidException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AgeNotValidException extends RuntimeException {
    public AgeNotValidException(String message) {
        super(message);
    }
}
