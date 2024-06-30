package com.registeruser.spring.register_user.util.userCreator;

import com.registeruser.spring.register_user.dto.userDto.UserPostDTO;

public class UserPostDTOCreator {
    public static UserPostDTO createUserPostDTO(){
        return UserPostDTO.builder()
                .name("Joseph")
                .age(18)
                .email("josephblack@gmail.com")
                .height(1.70)
                .build();
    }
}
