package com.registeruser.spring.register_user.controller;


import com.registeruser.spring.register_user.domain.user.User;
import com.registeruser.spring.register_user.dto.userDto.UserPostDTO;
import com.registeruser.spring.register_user.dto.userDto.UserPutDTO;
import com.registeruser.spring.register_user.service.UserService;
import com.registeruser.spring.register_user.util.userCreator.UserCreator;
import com.registeruser.spring.register_user.util.userCreator.UserPostDTOCreator;
import com.registeruser.spring.register_user.util.userCreator.UserPutDTOCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {
    @InjectMocks
    private UserController controller;
    @Mock
    private UserService service;

    @DisplayName("ListAll returns list of user when successful")
    @Test
    void findAll_ReturnListOfUsers_WhenSuccessful() {
        when(service.findAll()).thenReturn(List.of(UserCreator.createValidUser()));

        String name = UserCreator.createValidUser().getName();

        List<User> usersFind = controller.findAll().getBody();

        assertNotNull(usersFind);
        assertFalse(usersFind.isEmpty());
        assertEquals(name, usersFind.getFirst().getName());
        assertEquals(1, usersFind.size());
    }

    @Test
    @DisplayName("FindById returns user when successful")
    void findById_ReturnUser_WhenSuccessful() {
        when(service.findByIdOrThrowUserNotFoundException(any())).thenReturn(UserCreator.createValidUser());
        UUID id = UserCreator.createValidUser().getId();
        User user = controller.findById(UUID.randomUUID()).getBody();

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertNotNull(user.getId());
    }

    @Test
    @DisplayName("FindByName returns list of users when successful")
    void findByName_ReturnListOfUsers_WhenSuccessful() {
        when(service.findUserByNameOrThrowUserNotFoundException(anyString())).thenReturn(List.of(UserCreator.createValidUser()));

        String name = UserCreator.createValidUser().getName();
        List<User> usersFind = controller.findByName("what ever").getBody();

        assertNotNull(usersFind);
        assertFalse(usersFind.isEmpty());
        assertEquals(1, usersFind.size());
        assertEquals(name, usersFind.getFirst().getName());
    }

    @Test
    @DisplayName("FindByName returns empty list of users when user not found")
    void findByName_ReturnEmptyListOfUsers_WhenUserNotFound() {
        when(service.findUserByNameOrThrowUserNotFoundException(anyString())).thenReturn(Collections.emptyList());

        List<User> usersFind = controller.findByName("what ever").getBody();

        assertNotNull(usersFind);
        assertTrue(usersFind.isEmpty());
    }

    @Test
    @DisplayName("save returns user when successful")
    void save_ReturnUser_WhenSuccessful() {
        when(service.saveUser(any(UserPostDTO.class))).thenReturn(UserCreator.createValidUser());

        User user = controller.save(UserPostDTOCreator.createUserPostDTO()).getBody();

        assertNotNull(user);
        assertEquals(user.getId(), UserCreator.createValidUser().getId());
    }

    @Test
    @DisplayName("replace updates user when successful")
    void replace_UpdatesUser_WhenSuccessful() {
        doNothing().when(service).replace(any(UserPutDTO.class));

        assertThatCode(() -> controller.replace(UserPutDTOCreator.createUserPutDTO())).doesNotThrowAnyException();
        ResponseEntity<Void> replace = controller.replace(UserPutDTOCreator.createUserPutDTO());
        assertEquals(replace.getStatusCode(), HttpStatus.NO_CONTENT);

    }

    @Test
    @DisplayName("delete removes user when successful")
    void delete_UpdatesUser_WhenSuccessful() {
        doNothing().when(service).deleteById(any());

        assertThatCode(() -> controller.delete(UUID.randomUUID())).doesNotThrowAnyException();

        ResponseEntity<Void> delete = controller.delete(UUID.randomUUID());
        assertEquals(delete.getStatusCode(), HttpStatus.NO_CONTENT);
    }


}