package com.sweetsavvy.core.service;

import com.sweetsavvy.core.entity.UserEntity;
import com.sweetsavvy.core.model.LoginRequestDto;
import com.sweetsavvy.core.model.SignUpRequestDto;
import org.springframework.security.core.Authentication;

public interface UserService {

    UserEntity save(SignUpRequestDto signUpRequestDto);

    UserEntity findByEmail(String email);

    Authentication authenticateUser(LoginRequestDto loginRequestDto);
}
