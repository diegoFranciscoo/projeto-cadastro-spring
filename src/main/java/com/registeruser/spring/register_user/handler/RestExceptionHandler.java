package com.registeruser.spring.register_user.handler;

import com.registeruser.spring.register_user.exceptions.QuestionException.QuestionNotFound.QuestionNotFoundDetails;
import com.registeruser.spring.register_user.exceptions.QuestionException.QuestionNotFound.QuestionNotFoundException;
import com.registeruser.spring.register_user.exceptions.UserException.AgeNotValidException.AgeNotValidDetails;
import com.registeruser.spring.register_user.exceptions.UserException.AgeNotValidException.AgeNotValidException;
import com.registeruser.spring.register_user.exceptions.UserException.EmailNotValidException.EmailNotValidDetails;
import com.registeruser.spring.register_user.exceptions.UserException.EmailNotValidException.EmailNotValidException;
import com.registeruser.spring.register_user.exceptions.UserException.UserNotFoundException.UserNotFoundDetails;
import com.registeruser.spring.register_user.exceptions.UserException.UserNotFoundException.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserNotFoundDetails> handlerUserNotFound(UserNotFoundException userException){
        return new ResponseEntity<>(
                UserNotFoundDetails.builder()
                        .title("User not found.")
                        .timestamp(LocalDateTime.now())
                        .details(userException.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .build(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmailNotValidException.class)
    public ResponseEntity<EmailNotValidDetails> handlerEmailNotValid(EmailNotValidException emailException){
        return new ResponseEntity<>(
                EmailNotValidDetails.builder()
                        .title("Email not valid.")
                        .timestamp(LocalDateTime.now())
                        .details(emailException.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AgeNotValidException.class)
    public ResponseEntity<AgeNotValidDetails> handlerAgeNotValid(AgeNotValidException ageException){
        return new ResponseEntity<>(
                AgeNotValidDetails.builder()
                        .title("Age not valid")
                        .timestamp(LocalDateTime.now())
                        .details(ageException.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<QuestionNotFoundDetails> handlerQuestionNotFound(QuestionNotFoundException questionException){
        return new ResponseEntity<>(
                QuestionNotFoundDetails.builder()
                        .title("Question not Found")
                        .timestamp(LocalDateTime.now())
                        .details(questionException.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .build(), HttpStatus.NOT_FOUND);
    }
}
