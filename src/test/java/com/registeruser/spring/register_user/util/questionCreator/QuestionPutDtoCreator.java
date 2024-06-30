package com.registeruser.spring.register_user.util.questionCreator;

import com.registeruser.spring.register_user.dto.questionDto.QuestionPutDto;
import com.registeruser.spring.register_user.util.userCreator.UserCreator;

import java.util.UUID;

public class QuestionPutDtoCreator {
    private final static UUID ID = UUID.fromString("33bebf9c-f300-4158-b897-0050893daae5");
    public static QuestionPutDto createQuestionPutDto(){
        return QuestionPutDto
                .builder()
                .id(ID)
                .question("what ever")
                .userId(UserCreator.createValidUser().getId())
                .build();
    }
}
