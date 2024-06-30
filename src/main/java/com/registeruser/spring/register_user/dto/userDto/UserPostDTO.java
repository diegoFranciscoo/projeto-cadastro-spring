package com.registeruser.spring.register_user.dto.userDto;

import lombok.Builder;

@Builder
public record UserPostDTO(String name, String email, Integer age, Double height) {
}
