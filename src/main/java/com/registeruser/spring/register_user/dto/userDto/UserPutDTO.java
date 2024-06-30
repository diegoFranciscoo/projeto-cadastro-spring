package com.registeruser.spring.register_user.dto.userDto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserPutDTO(String name, String email, Integer age, Double height, UUID id) {
}
