package com.registeruser.spring.register_user.repository;

import com.registeruser.spring.register_user.domain.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

}
