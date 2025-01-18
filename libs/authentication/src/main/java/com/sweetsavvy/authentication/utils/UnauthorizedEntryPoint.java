package com.sweetsavvy.authentication.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sweetsavvy.authentication.model.ErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(UnauthorizedEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        logger.error("Unauthorized error: {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        var body = new ErrorDto(
                HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized",
                authException.getMessage(),
                request.getServletPath()
        );

        var responseStream = response.getOutputStream();
        var mapper = new ObjectMapper();
        mapper.writeValue(responseStream, body);
        responseStream.flush();
    }
}
