package com.registeruser.spring.register_user.service;

import com.registeruser.spring.register_user.domain.question.Question;
import com.registeruser.spring.register_user.exceptions.QuestionException.QuestionNotFound.QuestionNotFoundException;
import com.registeruser.spring.register_user.repository.QuestionRepository;
import com.registeruser.spring.register_user.util.questionCreator.QuestionCreator;
import com.registeruser.spring.register_user.util.questionCreator.QuestionPostDtoCreator;
import com.registeruser.spring.register_user.util.questionCreator.QuestionPutDtoCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class QuestionServiceTest {
    @InjectMocks
    QuestionService service;
    @Mock
    UserService userService;
    @Mock
    QuestionRepository repository;

    @Test
    @DisplayName("SaveQuestion save and return question when successful")
    void saveQuestion_ReturnQuestion_WhenSuccessful() {
        when(repository.save(any())).thenReturn(QuestionCreator.questionCreatorValid());

        Question question = service.saveQuestion(QuestionPostDtoCreator.createQuestionPostDto());

        assertNotNull(question);
        assertEquals(question.getUser().getId(), QuestionPostDtoCreator.createQuestionPostDto().userId());

    }

    @Test
    @DisplayName("findAll returns list of question when successful")
    void findAll_ReturnListQuestion_WhenSuccessful() {
        when(repository.findAll()).thenReturn((List.of(QuestionCreator.questionCreatorValid())));

        List<Question> questions = service.findAll();

        assertNotNull(questions);
        assertFalse(questions.isEmpty());
        assertEquals(1, questions.size());
        assertEquals(questions.getFirst().getId(), QuestionCreator.questionCreatorValid().getId());
    }

    @Test
    @DisplayName("findByIdOrThrowQuestionNotFoundException return question when successful")
    void findByIdOrThrowQuestionNotFoundException_ReturnQuestion_WhenSuccessful() {
        when(repository.findById(any())).thenReturn(Optional.of(QuestionCreator.questionCreatorValid()));
        UUID id = QuestionCreator.questionCreatorValid().getId();
        Question question = service.findByIdOrThrowQuestionNotFoundException(UUID.randomUUID());

        assertNotNull(question);
        assertNotNull(question.getId());
        assertEquals(id, question.getId());

    }

    @Test
    @DisplayName("findByIdOrThrowQuestionNotFoundException throw exception question when question not found")
    void findByIdOrThrowQuestionNotFoundException_ThrowException_WhenQuestionNotFound() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        assertThrows(QuestionNotFoundException.class, () -> service.findByIdOrThrowQuestionNotFoundException(UUID.randomUUID()));
    }

    @Test
    @DisplayName("replace updates question when successful")
    void replace_UpdatesQuestion_WhenSuccessful() {
        when(repository.findById(any())).thenReturn(Optional.of(QuestionCreator.questionCreatorValid()));
        assertThatCode(() -> service.replace(QuestionPutDtoCreator.createQuestionPutDto())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("delete removes question when successful")
    void delete_UpdatesQuestion_WhenSuccessful() {
        when(repository.findById(any())).thenReturn(Optional.of(QuestionCreator.questionCreatorValid()));
        doNothing().when(repository).delete(any());
        assertThatCode(() -> service.delete(UUID.randomUUID())).doesNotThrowAnyException();
    }


}