package org.example.be.Controller;

import lombok.RequiredArgsConstructor;
import org.example.be.model.User;
import org.example.be.repository.UserRepository;
import org.example.be.security.dto.LoginRequestDto;
import org.example.be.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
//@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
                          UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/api/login")
    public Map<String, String> login(@RequestBody LoginRequestDto request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (!user.isPresent() || !passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            return Map.of("error", "Invalid email or password");
        }

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.get().getUsername(), request.getPassword())
        );
        String token = jwtUtil.generateToken(user.get().getUsername());
        return Map.of("token", token);
    }
}
