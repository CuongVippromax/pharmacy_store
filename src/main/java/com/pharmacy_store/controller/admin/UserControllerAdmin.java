package com.pharmacy_store.controller.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy_store.domain.User;
import com.pharmacy_store.domain.dto.ResUserDTO;
import com.pharmacy_store.repository.UserRepository;
import com.pharmacy_store.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class UserControllerAdmin {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserControllerAdmin(UserService userService, PasswordEncoder passwordEncoder,
            UserRepository userRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public ResponseEntity<ResUserDTO> createUser(@RequestBody User user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(this.passwordEncoder.encode(user.getPassword()));
        ResUserDTO newResUser = new ResUserDTO();
        newResUser.setName(user.getName());
        newResUser.setEmail(user.getEmail());
        this.userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newResUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> list = this.userService.getAllUser();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        User user = this.userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.ok().body("Delete Succesfully !!!");
    }
}
