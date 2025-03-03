package org.example.be.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.be.model.User;
import org.example.be.repository.UserRepository;
import org.example.be.security.dto.JoinRequestDto;
import org.example.be.security.dto.LoginRequestDto;
import org.example.be.security.jwt.JwtUtil;
import org.example.be.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public String join(JoinRequestDto request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "이미 사용 중인 이메일 입니다.";
        }

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "이미 사용 중인 username 입니다.";
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());

        userRepository.save(user);

        return "가입을 환영합니다 !";
    }

    @Override
    public Map<String, String> login(LoginRequestDto request) {
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
