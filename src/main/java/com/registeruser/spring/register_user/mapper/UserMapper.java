package com.registeruser.spring.register_user.mapper;

import com.registeruser.spring.register_user.domain.user.User;
import com.registeruser.spring.register_user.dto.userDto.UserPostDTO;
import com.registeruser.spring.register_user.dto.userDto.UserPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User toUser(UserPostDTO userPostDTO);
    public abstract User toUser(UserPutDTO userPutDTO);

}
