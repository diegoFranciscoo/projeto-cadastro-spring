package com.registeruser.spring.register_user.controller;


import com.registeruser.spring.register_user.domain.question.Question;
import com.registeruser.spring.register_user.dto.questionDto.QuestionDTO;
import com.registeruser.spring.register_user.dto.questionDto.QuestionPutDto;
import com.registeruser.spring.register_user.service.QuestionService;
import com.registeruser.spring.register_user.util.questionCreator.QuestionCreator;
import com.registeruser.spring.register_user.util.questionCreator.QuestionPostDtoCreator;
import com.registeruser.spring.register_user.util.questionCreator.QuestionPutDtoCreator;
import com.registeruser.spring.register_user.util.userCreator.UserCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class QuestionControllerTest {
    @InjectMocks
    QuestionController controller;

    @Mock
    QuestionService service;

    @DisplayName("ListAll returns list of questions when successful")
    @Test
    void findAll_ReturnListOfQuestion_WhenSuccessful() {
        when(service.findAll()).thenReturn(List.of(QuestionCreator.questionCreatorValid()));


        UUID id = QuestionCreator.questionCreatorValid().getUser().getId();
        String question = QuestionCreator.questionCreatorValid().getQuestion();

        List<Question> questions = controller.findAll().getBody();

        assertNotNull(questions);
        assertFalse(questions.isEmpty());
        assertEquals(question, questions.getFirst().getQuestion());
        assertEquals(UserCreator.createValidUser().getId(), id);
        assertEquals(1, questions.size());
    }

    @Test
    @DisplayName("FindById returns question when successful")
    void findById_ReturnQuestion_WhenSuccessful() {
        when(service.findByIdOrThrowQuestionNotFoundException(any())).thenReturn(QuestionCreator.questionCreatorValid());
        UUID id = QuestionCreator.questionCreatorValid().getId();
        Question question = controller.findById(UUID.randomUUID()).getBody();

        assertNotNull(question);
        assertEquals(id, question.getId());
        assertNotNull(question.getId());
    }

    @Test
    @DisplayName("save returns question when successful")
    void save_ReturnQuestion_WhenSuccessful() {
        when(service.saveQuestion(any(QuestionDTO.class))).thenReturn(QuestionCreator.questionCreatorValid());

        Question question = controller.save(QuestionPostDtoCreator.createQuestionPostDto()).getBody();

        assertNotNull(question);
        assertEquals(question.getId(), QuestionCreator.questionCreatorValid().getId());
        assertEquals(question.getUser().getId(), QuestionCreator.questionCreatorValid().getUser().getId());
        assertEquals(question.getQuestion(), QuestionCreator.questionCreatorValid().getQuestion());
    }

    @Test
    @DisplayName("replace updates question when successful")
    void replace_UpdatesQuestion_WhenSuccessful() {
        doNothing().when(service).replace(any(QuestionPutDto.class));

        assertThatCode(() -> controller.replace(QuestionPutDtoCreator.createQuestionPutDto())).doesNotThrowAnyException();
        ResponseEntity<Void> replace = controller.replace(QuestionPutDtoCreator.createQuestionPutDto());
        assertEquals(HttpStatus.NO_CONTENT,replace.getStatusCode());

    }

    @Test
    @DisplayName("delete removes question when successful")
    void delete_UpdatesQuestion_WhenSuccessful() {
        doNothing().when(service).delete(any());

        assertThatCode(() -> controller.delete(UUID.randomUUID())).doesNotThrowAnyException();

        ResponseEntity<Void> delete = controller.delete(UUID.randomUUID());
        assertEquals(HttpStatus.NO_CONTENT, delete.getStatusCode());
    }

}