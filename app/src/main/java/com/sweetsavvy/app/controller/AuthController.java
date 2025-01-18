package com.sweetsavvy.app.controller;

import com.sweetsavvy.authentication.utils.JwtUtil;
import com.sweetsavvy.core.model.JwtResponse;
import com.sweetsavvy.core.model.LoginRequestDto;
import com.sweetsavvy.core.model.SignUpRequestDto;
import com.sweetsavvy.core.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final JwtUtil jwtUtils;

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequestDto signUpRequest, HttpServletRequest request) {

        if (userService.findByEmail(signUpRequest.email()) != null) {
            return ResponseEntity.badRequest()
                    .body("Error: Username is already taken!");
        }

        var user = userService.save(signUpRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDto loginRequest) {
        var authentication = userService.authenticateUser(loginRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        var userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt, "Bearer", userDetails.getUsername()));

    }
}