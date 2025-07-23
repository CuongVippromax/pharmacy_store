package com.pharmacy_store.service.impl;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.pharmacy_store.domain.User;
import com.pharmacy_store.service.UserService;

@Component("userDetailsService")
public class UserDetailsCustom implements UserDetailsService {
    private final UserService userService;

    public UserDetailsCustom(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // kiểm tra người dùng trong db
        User getUser = this.userService.checkUserforUserDetails(username);
        if (getUser == null) {
            throw new UsernameNotFoundException("Không tồn tại người dùng ");
        } else {
            return new org.springframework.security.core.userdetails.User(getUser.getEmail(), getUser.getPassword(),
                    Collections.emptyList());
        }
    }

}
