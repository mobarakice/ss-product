package com.sweetsavvy.core.service.impl;

import com.sweetsavvy.core.entity.UserEntity;
import com.sweetsavvy.core.model.LoginRequestDto;
import com.sweetsavvy.core.model.SignUpRequestDto;
import com.sweetsavvy.core.repository.UserRepository;
import com.sweetsavvy.core.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    private final ModelMapper modelMapper;

    private final AuthenticationManager authenticationManager;

    @Transactional
    @Override
    public UserEntity save(SignUpRequestDto signUpRequestDto) {
        var user = modelMapper.map(signUpRequestDto, UserEntity.class);
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    @Override
    public Authentication authenticateUser(LoginRequestDto loginRequestDto) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.email(), loginRequestDto.password())
        );
    }
}
