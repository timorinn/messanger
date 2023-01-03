package com.tim.user_service.repository;

import com.tim.user_service.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryIntegrationTest {

    @Autowired UserRepository userRepository;

    UUID firstUserId = UUID.randomUUID();
    UUID secondUserId = UUID.randomUUID();

    @BeforeEach
    public void setup() {
        User firstUser = new User();
        firstUser.setName("Nick");
        firstUser.setId(firstUserId);
        userRepository.save(firstUser);

        User secondUser = new User();
        secondUser.setId(secondUserId);
        secondUser.setName("Kek");
        userRepository.save(secondUser);
    }

    @Test
    public void userRepository_count() {
        long count = userRepository.count();

        assertEquals(2, count);
    }

    @Test
    public void userRepository_findById() {
        User firstUser = userRepository.findById(firstUserId).get();
        User secondUser = userRepository.findById(secondUserId).get();

        assertEquals("Nick", firstUser.getName());
        assertEquals(firstUserId, firstUser.getId());
        assertEquals(0, firstUser.getVersion());

        assertEquals("Kek", secondUser.getName());
        assertEquals(secondUserId, secondUser.getId());
        assertEquals(0, secondUser.getVersion());
    }

    @Test
    public void userRepository_update() {
        User user = userRepository.findById(firstUserId).get();
        user.setName("Nickitochka");
        userRepository.saveAndFlush(user);

        User updatedUser = userRepository.findById(firstUserId).get();
        assertEquals("Nickitochka", updatedUser.getName());
        assertEquals(1, updatedUser.getVersion());
    }
}