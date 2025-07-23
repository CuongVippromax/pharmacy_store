package com.pharmacy_store.controller.admin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy_store.domain.User;
import com.pharmacy_store.domain.dto.ResCrearteUserDTO;
import com.pharmacy_store.domain.dto.pagination.Meta;
import com.pharmacy_store.domain.dto.pagination.ResultPagination;
import com.pharmacy_store.repository.UserRepository;
import com.pharmacy_store.service.UserService;
import com.turkraft.springfilter.boot.Filter;

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

    @PostMapping("/register")
    public ResponseEntity<ResCrearteUserDTO> createUser(@RequestBody User user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(this.passwordEncoder.encode(user.getPassword()));
        ResCrearteUserDTO newResUser = new ResCrearteUserDTO();
        newResUser.setName(user.getName());
        newResUser.setEmail(user.getEmail());
        this.userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newResUser);
    }

    @GetMapping("/users")
    public ResponseEntity<ResultPagination> getAllUser(@Filter Specification<User> spec, Pageable pageable) {

        return ResponseEntity.ok().body(this.userService.getAllUser(spec, pageable));
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
