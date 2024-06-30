package com.registeruser.spring.register_user.exceptions.QuestionException.QuestionNotFound;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(String message) {
        super(message);
    }
}
