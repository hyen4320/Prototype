package org.example.be.service;

import org.example.be.security.dto.JoinRequestDto;
import org.example.be.security.dto.LoginRequestDto;

import java.util.Map;

public interface UserService {

    String join(JoinRequestDto request);
    Map<String, String> login(LoginRequestDto request);
}
