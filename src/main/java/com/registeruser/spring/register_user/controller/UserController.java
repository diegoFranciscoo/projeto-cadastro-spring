package com.registeruser.spring.register_user.controller;


import com.registeruser.spring.register_user.domain.user.User;
import com.registeruser.spring.register_user.dto.userDto.UserPostDTO;
import com.registeruser.spring.register_user.dto.userDto.UserPutDTO;
import com.registeruser.spring.register_user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.findByIdOrThrowUserNotFoundException(id));
    }

    @GetMapping(path = "{name}")
    public ResponseEntity<List<User>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.findUserByNameOrThrowUserNotFoundException(name));
    }


    @PostMapping
    public ResponseEntity<User> save(@RequestBody UserPostDTO userPostDTO) {
        return new ResponseEntity<>(userService.saveUser(userPostDTO),HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody UserPutDTO userPutDTO) {
        userService.replace(userPutDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
