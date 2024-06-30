package com.registeruser.spring.register_user.service;

import com.registeruser.spring.register_user.domain.user.User;
import com.registeruser.spring.register_user.dto.userDto.UserPostDTO;
import com.registeruser.spring.register_user.dto.userDto.UserPutDTO;
import com.registeruser.spring.register_user.exceptions.UserException.AgeNotValidException.AgeNotValidException;
import com.registeruser.spring.register_user.exceptions.UserException.EmailNotValidException.EmailNotValidException;
import com.registeruser.spring.register_user.exceptions.UserException.UserNotFoundException.UserNotFoundException;
import com.registeruser.spring.register_user.mapper.UserMapper;
import com.registeruser.spring.register_user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User saveUser(UserPostDTO userDTO) {
        validateUser(userDTO);
        return userRepository.save(UserMapper.INSTANCE.toUser(userDTO));
    }
    public User findByIdOrThrowUserNotFoundException(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found."));

    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findUserByNameOrThrowUserNotFoundException(String name) {
        return userRepository.findUserByName(name).orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public void deleteById(UUID id) {
        userRepository.delete(findByIdOrThrowUserNotFoundException(id));
    }
    public void replace(UserPutDTO userPutDTO){
        findByIdOrThrowUserNotFoundException(userPutDTO.id());
        userRepository.save(UserMapper.INSTANCE.toUser(userPutDTO));
    }

    private void validateUser(UserPostDTO user) {
        var pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        var matcher = pattern.matcher(user.email());
        if(!matcher.find()){
            throw new EmailNotValidException("The email is not valid.");
        }
        if (user.age() < 18){
            throw new AgeNotValidException("you need to be at least 18 years old.");
        }
    }

}
