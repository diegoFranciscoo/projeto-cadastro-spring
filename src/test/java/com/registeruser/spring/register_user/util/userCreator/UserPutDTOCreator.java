package com.registeruser.spring.register_user.util.userCreator;

import com.registeruser.spring.register_user.dto.userDto.UserPutDTO;

import java.util.UUID;

public class UserPutDTOCreator {
    private static final UUID id = UUID.fromString("31f0e1cd-2118-4d30-bd2c-25eeafef08bd");

    public static UserPutDTO createUserPutDTO() {
        return UserPutDTO.builder()
                .id(id)
                .name("Joseph Black")
                .age(20)
                .email("josephblack@gmail.com")
                .height(1.65)
                .build();
    }
}
