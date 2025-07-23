package com.pharmacy_store.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pharmacy_store.domain.User;
import com.pharmacy_store.domain.dto.pagination.Meta;
import com.pharmacy_store.domain.dto.pagination.ResultPagination;
import com.pharmacy_store.exception.CustomMessageError;
import com.pharmacy_store.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) throws CustomMessageError {
        if (this.userRepository.existsByEmail(user.getEmail()) == false) {
            throw new CustomMessageError("Email này đã tồn tại trong hệ thống");
        } else {
            User newUser = new User();
            newUser.setName(user.getName());
            newUser.setEmail(user.getEmail());
            String hashPassword = this.passwordEncoder.encode(user.getPassword());
            newUser.setPassword(hashPassword);
            return newUser;
        }
    }

    public ResultPagination getAllUser(Specification<User> spec, Pageable pageable) {
        Page<User> listUser = this.userRepository.findAll(spec, pageable);
        ResultPagination res = new ResultPagination();
        Meta meta = new Meta();
        meta.setPage(pageable.getPageNumber() + 1);
        meta.setPageSize(pageable.getPageSize());
        meta.setPages(listUser.getTotalPages());
        meta.setTotal(listUser.getTotalElements());

        res.setMeta(meta);
        res.setResult(listUser.getContent());
        return res;
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
            this.userRepository.save(updateUser);
            return updateUser;
        } else {
            return null;
        }

    }

    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    public User checkUserforUserDetails(String email) {
        return this.userRepository.findByEmail(email);
    }
}
