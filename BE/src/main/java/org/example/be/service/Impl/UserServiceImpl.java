package org.example.be.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.be.security.dto.ResMessage;
import org.example.be.security.dto.user.ReqUser;
import org.example.be.model.User;
import org.example.be.repository.UserRepository;
import org.example.be.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Not found user"));
    }

    @Override
    public User getUserByUsername(String name) {
        return userRepository.findByUsername(name).orElseThrow(()-> new NoSuchElementException("Not found user"));
    }

    @Override
    public ResMessage saveUser(ReqUser user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            throw new NoSuchElementException("Not found user");
        }
        User newUser = User.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword()).build();
        userRepository.save(newUser);
        ResMessage resMessage = new ResMessage();
        resMessage.setMessage("save user success");
        return resMessage;
    }

    @Override
    public ResMessage updateUser(ReqUser user) {
        User newUser=userRepository.findByUsername(user.getUsername()).orElseThrow(()-> new NoSuchElementException("Not found user"));
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        userRepository.save(newUser);
        ResMessage resMessage = new ResMessage();
        resMessage.setMessage("update user success");
        return resMessage;
    }

    @Override
    public ResMessage deleteUser(Long id) {
        userRepository.deleteById(id);
        ResMessage resMessage = new ResMessage();
        resMessage.setMessage("delete user success");
        return resMessage;
    }
}
