package com.registeruser.spring.register_user.util.questionCreator;

import com.registeruser.spring.register_user.domain.question.Question;
import com.registeruser.spring.register_user.util.userCreator.UserCreator;

import java.util.UUID;

public class QuestionCreator {
    private final static UUID ID = UUID.fromString("33bebf9c-f300-4158-b897-0050893daae5");
    public static Question questionCreatorValid(){
        return Question.builder()
                .id(ID)
                .question("what ever")
                .user(UserCreator.createValidUser())
                .build();
    }
}
