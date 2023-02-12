package com.tim.user_service.service;

import com.tim.user_service.entity.User;
import com.tim.user_service.exception.UserNotFoundException;
import com.tim.user_service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ActiveProfiles("test")
@Import(UserService.class)
class UserServiceIntegrationTest {

    @Autowired UserRepository userRepository;
    @Autowired UserService userService;

    UUID userId = UUID.randomUUID();

    @BeforeEach
    public void setup() {
        User user = new User();
        user.setName("Nick");
        user.setId(userId);
        userRepository.save(user);
    }

    @Test
    public void getUser_ok() {
        userService.getUserById(userId);
    }

    @Test
    public void getUser_userNotExists_exception() {
        UUID randomId = UUID.randomUUID();
        UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class, () -> userService.getUserById(randomId));

        assertEquals(format("User with id %s not found.", randomId), userNotFoundException.getMessage());
    }
}