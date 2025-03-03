package org.example.be.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.be.model.User;
import org.example.be.repository.UserRepository;
import org.example.be.security.dto.JoinRequestDto;
import org.example.be.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

}
