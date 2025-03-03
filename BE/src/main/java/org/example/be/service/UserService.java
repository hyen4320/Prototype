package org.example.be.service;

import org.example.be.security.dto.JoinRequestDto;

public interface UserService {

    String join(JoinRequestDto request);
}
