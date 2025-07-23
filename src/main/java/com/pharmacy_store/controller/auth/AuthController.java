package com.pharmacy_store.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy_store.domain.User;
import com.pharmacy_store.domain.dto.LoginDTO;
import com.pharmacy_store.domain.dto.ResLoginDTO;
import com.pharmacy_store.domain.dto.RestResponse;
import com.pharmacy_store.domain.dto.ResCrearteUserDTO;
import com.pharmacy_store.repository.UserRepository;
import com.pharmacy_store.util.JWTUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JWTUtil jwtUtil;

    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder,
            PasswordEncoder passwordEncoder, UserRepository userRepository, JWTUtil jwtUtil) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<ResLoginDTO> login(@RequestBody LoginDTO loginDTO) {
        // Nạp input gồm username/password vào Security
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword());
        // xác thực người dùng => cần viết hàm loadUserByUsername
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // create jwt token
        String acessToken = this.jwtUtil.crateToken(authentication);
        // Nạp thông tin người dùng vào SecurityContextHolder để sau này không cần login
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ResLoginDTO responseUser = new ResLoginDTO();
        responseUser.setEmail(loginDTO.getUsername());
        responseUser.setAccessToken(acessToken);
        return ResponseEntity.ok().body(responseUser);

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

}
