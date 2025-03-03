package org.example.be.controller;


import lombok.RequiredArgsConstructor;
import org.example.be.model.User;
import org.example.be.security.PasswordConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final PasswordConfig passwordConfig;

    @PostMapping("/join")
    public void join(User user) {

    }
}
