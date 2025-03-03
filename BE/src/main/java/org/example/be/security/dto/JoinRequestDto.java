package org.example.be.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinRequestDto {
    private String email;
    private String password;
    private String username;
}
