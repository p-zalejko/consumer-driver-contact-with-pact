package com.gmail.pzalejko.pactprovider.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    ResponseEntity<User> findById(@PathVariable("id") long id) {
        return userService
                .findUserById(id)
                .map(ResponseEntity::ok) // no DTO class, let's keep it simple
                .orElse(ResponseEntity.notFound().build());
    }
}
