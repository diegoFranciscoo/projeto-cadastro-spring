package com.registeruser.spring.register_user.util.questionCreator;

import com.registeruser.spring.register_user.dto.questionDto.QuestionDTO;
import com.registeruser.spring.register_user.util.userCreator.UserCreator;

public class QuestionPostDtoCreator {
    public static QuestionDTO createQuestionPostDto(){
        return QuestionDTO
                .builder()
                .question("what ever")
                .userId(UserCreator.createValidUser().getId())
                .build();
    }
}
