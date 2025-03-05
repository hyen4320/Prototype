package org.example.be.controller;


import lombok.RequiredArgsConstructor;
import org.example.be.security.dto.ResMessage;
import org.example.be.model.User;
import org.example.be.security.dto.user.ResUser;
import org.example.be.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<ResUser> getUserById(Long id) {
        User user =userService.getUserById(id);
        ResUser sendUser = ResUser.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername()).build();
        return ResponseEntity.ok(sendUser);
    }


}
