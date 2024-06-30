package com.registeruser.spring.register_user.mapper;

import com.registeruser.spring.register_user.domain.question.Question;
import com.registeruser.spring.register_user.domain.user.User;
import com.registeruser.spring.register_user.dto.questionDto.QuestionDTO;
import com.registeruser.spring.register_user.dto.questionDto.QuestionPutDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class QuestionMapper {
    public static final QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    @Mapping(target = "user", ignore = true)
    public abstract Question toQuestion(QuestionDTO questionDTO);

    @Mapping(target = "user", ignore = true)
    public abstract Question toQuestion(QuestionPutDto questionPutDto);
}
