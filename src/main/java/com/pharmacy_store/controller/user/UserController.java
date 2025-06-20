package com.pharmacy_store.controller.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy_store.domain.User;
import com.pharmacy_store.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String updateUser(User user, long id) {
        User getUser = this.userRepository.findById(id).get();
        getUser.setName(user.getName());
        getUser.setEmail(user.getEmail());
        getUser.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return "Update Profile Successfully !!!";
    }

    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }
}
