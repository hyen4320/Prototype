package org.example.be.service;

import org.example.be.security.dto.ResMessage;
import org.example.be.security.dto.user.ReqUser;
import org.example.be.model.User;

public interface UserService {
    User getUserById(Long id);
    User getUserByUsername(String name);
    ResMessage saveUser(ReqUser user);
    ResMessage updateUser(ReqUser user);
    ResMessage deleteUser(Long id);
}
