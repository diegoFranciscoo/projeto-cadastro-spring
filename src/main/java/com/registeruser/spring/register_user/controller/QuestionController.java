package com.registeruser.spring.register_user.controller;


import com.registeruser.spring.register_user.domain.question.Question;
import com.registeruser.spring.register_user.dto.questionDto.QuestionDTO;
import com.registeruser.spring.register_user.dto.questionDto.QuestionPutDto;
import com.registeruser.spring.register_user.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
@Log4j2
public class QuestionController {
    private final QuestionService service;

    @GetMapping
    public ResponseEntity<List<Question>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Question> findById(@PathVariable UUID id){
        return ResponseEntity.ok(service.findByIdOrThrowQuestionNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<Question> save(@RequestBody QuestionDTO questionDTO) {
        return new ResponseEntity<>(service.saveQuestion(questionDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody QuestionPutDto questionPutDto) {
        service.replace(questionPutDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
