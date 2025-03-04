package org.example.be.Controller;

import lombok.RequiredArgsConstructor;
import org.example.be.security.dto.JoinRequestDto;
import org.example.be.security.dto.LoginRequestDto;
import org.example.be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/join")
    public ResponseEntity<String> join(@RequestBody JoinRequestDto request) {
        return ResponseEntity.ok(userService.join(request));
    }

    @PostMapping("/api/login")
    public Map<String, String> login(@RequestBody LoginRequestDto request) {
        return userService.login(request);
    }
}
