package com.ljs.blog.domain.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource("classpath:application-test.yml")
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void saveTest() {
        // given
        String username = "username";
        String password = "password";
        Role role = Role.GUEST;
        String email = "test@email.com";

        userRepository.save(User.builder().username(username).password(password).role(role).email(email).build());

        // when
        List<User> userList = userRepository.findAll();

        // then
        User user = userList.get(0);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getRole()).isEqualTo(role);
    }

}