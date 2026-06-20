package com.nitish.blogManagement.controller;

import com.nitish.blogManagement.config.AuthResponse;
import com.nitish.blogManagement.config.JwtService;
import com.nitish.blogManagement.model.LoginRequest;
import com.nitish.blogManagement.model.User;
import com.nitish.blogManagement.repo.UserRepo;
import com.nitish.blogManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo repo;

    @PostMapping("/register")
    public User register(@RequestBody User user){

        if(repo.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }

        String rawPassword = user.getPassword();

        String encodedPassword =
                passwordEncoder.encode(rawPassword);

        System.out.println("RAW PASSWORD = " + rawPassword);
        System.out.println("ENCODED PASSWORD = " + encodedPassword);
        System.out.println(
                "MATCH = " +
                        passwordEncoder.matches(
                                rawPassword,
                                encodedPassword
                        )
        );

        user.setPassword(encodedPassword);

        return userService.addUser(user);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request){

        User user = repo.findByEmail(request.getEmail()).get();

        System.out.println("EMAIL = " + request.getEmail());
        System.out.println("DB EMAIL = " + user.getEmail());

        System.out.println("RAW = " + request.getPassword());
        System.out.println("HASH = " + user.getPassword());


        boolean match = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        System.out.println("Match = " + match);

        System.out.println(
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                )
        );

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token =
                jwtService.generateToken(
                        request.getEmail()
                );

        return new AuthResponse(token);
    }
}
