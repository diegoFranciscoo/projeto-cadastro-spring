package com.registeruser.spring.register_user.service;

import com.registeruser.spring.register_user.domain.user.User;
import com.registeruser.spring.register_user.exceptions.UserException.UserNotFoundException.UserNotFoundException;
import com.registeruser.spring.register_user.mapper.UserMapper;
import com.registeruser.spring.register_user.repository.UserRepository;
import com.registeruser.spring.register_user.util.userCreator.UserCreator;
import com.registeruser.spring.register_user.util.userCreator.UserPostDTOCreator;
import com.registeruser.spring.register_user.util.userCreator.UserPutDTOCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {
    @InjectMocks
    private UserService service;
    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper userMapper;

    @DisplayName("findAll returns list of user when successful")
    @Test
    void findAll_ReturnListOfUsers_WhenSuccessful() {
        when(repository.findAll()).thenReturn(List.of(UserCreator.createValidUser()));

        String name = UserCreator.createValidUser().getName();

        List<User> usersFind = repository.findAll();

        assertNotNull(usersFind);
        assertFalse(usersFind.isEmpty());
        assertEquals(name, usersFind.getFirst().getName());
        assertEquals(1, usersFind.size());
    }

    @Test
    @DisplayName("findByIdOrThrowUserNotFoundException returns user when successful")
    void findByIdOrThrowUserNotFoundException_ReturnUser_WhenSuccessful() {
        when(repository.findById(any())).thenReturn(Optional.of(UserCreator.createValidUser()));
        UUID id = UserCreator.createValidUser().getId();
        User user = service.findByIdOrThrowUserNotFoundException(UUID.randomUUID());

        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals(id, user.getId());
    }

    @Test
    @DisplayName("findByIdOrThrowUserNotFoundException throw UserNotFoundException when user not found")
    void findByIdOrThrowUserNotFoundException_ThrowUserNotFoundException_WhenUserNotFound() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,() -> service.findByIdOrThrowUserNotFoundException(UUID.randomUUID()));
    }

    @Test
    @DisplayName("FindByName returns list of users when successful")
    void findByName_ReturnListOfUsers_WhenSuccessful() {
        when(repository.findUserByName(anyString())).thenReturn(Optional.of(List.of(UserCreator.createValidUser())));

        String name = UserCreator.createValidUser().getName();
        List<User> usersFind = service.findUserByNameOrThrowUserNotFoundException("what ever");

        assertNotNull(usersFind);
        assertFalse(usersFind.isEmpty());
        assertEquals(1, usersFind.size());
        assertEquals(name, usersFind.getFirst().getName());
    }

    @Test
    @DisplayName("FindByName throw UserNotFoundException when user not found")
    void findByName_ReturnEmptyListOfUsers_WhenUserNotFound() {
        when(repository.findUserByName(anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.findUserByNameOrThrowUserNotFoundException("what ever"));
    }

    @Test
    @DisplayName("save returns user when successful")
    void save_ReturnUser_WhenSuccessful() {
        when(repository.save(any(User.class))).thenReturn(UserCreator.createValidUser());

        User user = service.saveUser(UserPostDTOCreator.createUserPostDTO());

        assertNotNull(user);
        assertEquals(user.getId(), UserCreator.createValidUser().getId());
    }

    @Test
    @DisplayName("replace updates user when successful")
    void replace_UpdatesUser_WhenSuccessful() {
        when(repository.findById(any())).thenReturn(Optional.of(UserCreator.createValidUser()));
        assertThatCode(() -> service.replace(UserPutDTOCreator.createUserPutDTO())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("delete removes user when successful")
    void delete_UpdatesUser_WhenSuccessful() {
        when(repository.findById(any())).thenReturn(Optional.of(UserCreator.createValidUser()));
        doNothing().when(repository).delete(any());
        assertThatCode(() -> service.deleteById(UUID.randomUUID())).doesNotThrowAnyException();

    }
}