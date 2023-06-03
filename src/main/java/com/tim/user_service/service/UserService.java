package com.tim.user_service.service;

import com.tim.user_service.entity.User;
import com.tim.user_service.exception.UserNotFoundException;
import com.tim.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public User getUserById(UUID id) {
    return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(format("User with id %s not found.", id)));
  }

  @Transactional(readOnly = true)
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Transactional()
  public User addUser(User user) {
    return userRepository.save(user);
  }

  @Transactional
  public User updateUser(User user) {
    User userEntity = userRepository.findById(user.getId()).orElseThrow(
            () -> new UserNotFoundException(format("User with id %s not found.", user.getId())));
    userEntity.setName(user.getName());
    userEntity = userRepository.save(userEntity);
    return userEntity;
  }
}