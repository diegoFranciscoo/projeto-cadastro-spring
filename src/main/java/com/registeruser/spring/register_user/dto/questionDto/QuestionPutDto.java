package com.registeruser.spring.register_user.dto.questionDto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record QuestionPutDto(UUID userId, String question, UUID id) {
}
