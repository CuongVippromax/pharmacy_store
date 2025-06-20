package com.pharmacy_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pharmacy_store.domain.User;
import com.pharmacy_store.repository.UserRepository;
import com.pharmacy_store.util.error.GlobalErrorMessage;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) throws GlobalErrorMessage {
        if (this.userRepository.existsByEmail(user.getEmail()) == false) {
            throw new GlobalErrorMessage("Email này đã tồn tại trong hệ thống");
        } else {
            User newUser = new User();
            newUser.setName(user.getName());
            newUser.setEmail(user.getEmail());
            String hashPassword = this.passwordEncoder.encode(user.getPassword());
            newUser.setPassword(hashPassword);
            return newUser;
        }
    }

    public List<User> getAllUser() {
        List<User> listUser = this.userRepository.findAll();
        return listUser;
    }

    public User getUserById(long id) {
        Optional<User> getUser = this.userRepository.findById(id);
        if (getUser.isPresent()) {
            User gUser = getUser.get();
            return gUser;
        } else {
            return null;
        }
    }

    public User updateUser(User user, long id) {
        Optional<User> getUser = this.userRepository.findById(id);
        if (getUser.isPresent()) {
            User updateUser = getUser.get();
            updateUser.setName(user.getName());
            updateUser.setEmail(user.getEmail());
            updateUser.setPassword(this.passwordEncoder.encode(user.getPassword()));
            return updateUser;
        } else {
            return null;
        }

    }

    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }
}
