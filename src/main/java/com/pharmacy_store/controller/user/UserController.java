package com.pharmacy_store.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy_store.domain.User;
import com.pharmacy_store.repository.UserRepository;
import com.pharmacy_store.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@RequestBody User user, @PathVariable long id) {
        User updatedUser = this.userService.updateUser(user, id);
        return ResponseEntity.ok().body("Updated !!!");
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.ok().body("Delete Succesfully !!!");
    }
}
