package com.registeruser.spring.register_user.util.userCreator;

import com.registeruser.spring.register_user.domain.user.User;

import java.util.UUID;

public class UserCreator {
    private static final UUID id = UUID.fromString("31f0e1cd-2118-4d30-bd2c-25eeafef08bd");

    public static User createValidUser(){
        return User.builder()
                .id(id)
                .name("Joseph")
                .age(18)
                .email("josephblack@gmail.com")
                .height(1.70)
                .build();
    }

}
