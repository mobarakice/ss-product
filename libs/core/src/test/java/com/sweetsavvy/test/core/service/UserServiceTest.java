package com.sweetsavvy.test.core.service;

import com.sweetsavvy.core.entity.UserEntity;
import com.sweetsavvy.core.model.LoginRequestDto;
import com.sweetsavvy.core.model.SignUpRequestDto;
import com.sweetsavvy.core.repository.UserRepository;
import com.sweetsavvy.core.service.UserService;
import com.sweetsavvy.core.service.impl.UserServiceImpl;
import com.sweetsavvy.test.core.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TestConfig.class)
public class UserServiceTest {
    @MockBean
    UserRepository userRepository;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    PasswordEncoder encoder;

    @Autowired
    ModelMapper modelMapper;

    UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, encoder, modelMapper, authenticationManager);
    }

    @Test
    void saveUser_notExistCreateNew_existUpdate() {
        var user = prepareTestUserEntity();

        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        var dto = prepareTestSignUpRequestDto();
        var result = userService.save(dto);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(user.getEmail());

        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void findUser_exist() {
        var user = Optional.of(prepareTestUserEntity());

        when(userRepository.findByEmail(any(String.class))).thenReturn(user);

        var email = "test@news360horizon.com";
        var result = userService.findByEmail(email);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);

        verify(userRepository, times(1)).findByEmail(any(String.class));
    }

    @Test
    void findUser_notExist() {
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());

        var email = "nonexistent@news360horizon.com";
        var result = userService.findByEmail(email);

        assertThat(result).isNull();

        verify(userRepository, times(1)).findByEmail(any(String.class));
    }

    @Test
    void authenticateUser() {
        var loginRequestDto = new LoginRequestDto("test@news360horizon.com", "test@1234");
        var authentication = new UsernamePasswordAuthenticationToken(loginRequestDto.email(), loginRequestDto.password());

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        var result = userService.authenticateUser(loginRequestDto);
        assertThat(result).isNotNull();
    }

    SignUpRequestDto prepareTestSignUpRequestDto() {
        return new SignUpRequestDto(
                "test@news360horizon.com",
                "test@1234",
                Set.of(1L),
                "Baridhara, Dhaka",
                "test",
                "01700000000"
        );
    }

    UserEntity prepareTestUserEntity() {
        var user = new UserEntity();
        user.setId(1L);
        user.setName("test");
        user.setEmail("test@news360horizon.com");
        user.setPassword("test@1234");
        user.setAddress("Baridhara, Dhaka");
        user.setPhoneNo("01700000000");
        return user;
    }
}