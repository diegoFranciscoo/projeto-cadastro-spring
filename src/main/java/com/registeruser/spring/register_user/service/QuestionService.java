package com.registeruser.spring.register_user.service;

import com.registeruser.spring.register_user.domain.question.Question;
import com.registeruser.spring.register_user.domain.user.User;
import com.registeruser.spring.register_user.dto.questionDto.QuestionDTO;
import com.registeruser.spring.register_user.dto.questionDto.QuestionPutDto;
import com.registeruser.spring.register_user.exceptions.QuestionException.QuestionNotFound.QuestionNotFoundException;
import com.registeruser.spring.register_user.mapper.QuestionMapper;
import com.registeruser.spring.register_user.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final UserService userService;
    private final QuestionRepository repository;

    public Question saveQuestion(QuestionDTO questionDTO) {
        User user = userService.findByIdOrThrowUserNotFoundException(questionDTO.userId());
        Question question = QuestionMapper.INSTANCE.toQuestion(questionDTO);
        question.setUser(user);
        return repository.save(question);
    }

    public List<Question> findAll() {
        return repository.findAll();
    }

    public Question findByIdOrThrowQuestionNotFoundException(UUID id) {
        return repository.findById(id).orElseThrow(() -> new QuestionNotFoundException("Question ID not found"));
    }

    public void delete(UUID id) {
        findByIdOrThrowQuestionNotFoundException(id);
        repository.delete(findByIdOrThrowQuestionNotFoundException(id));
    }

    public void replace(QuestionPutDto questionPutDto) {
        findByIdOrThrowQuestionNotFoundException(questionPutDto.id());
        User user = userService.findByIdOrThrowUserNotFoundException(questionPutDto.userId());
        Question question = QuestionMapper.INSTANCE.toQuestion(questionPutDto);
        question.setUser(user);
        repository.save(question);
    }
}
