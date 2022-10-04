package com.tim.messenger.service;

import com.tim.messenger.entity.User;
import com.tim.messenger.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    
    public User getUserById(UUID id) {
        // TODO: 04.10.2022
        return userRepository.findById(id).orElseThrow();
    }
}