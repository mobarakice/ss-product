//package com.sweetsavvy.app.controller;
//
//import com.sweetsavvy.app.SweetSavvyApp;
//import com.sweetsavvy.core.entity.UserEntity;
//import com.sweetsavvy.core.model.JwtResponse;
//import com.sweetsavvy.core.model.LoginRequestDto;
//import com.sweetsavvy.core.model.SignUpRequestDto;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.web.client.ResourceAccessException;
//
//import java.net.HttpRetryException;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest(classes = SweetSavvyApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(scripts = {"/users/clear-data.sql", "/users/test-data.sql"})
//public class AuthControllerIntegrationTest {
//
//    @LocalServerPort
//    int port;
//
//    @Autowired
//    TestRestTemplate restTemplate;
//
//    String baseUrl;
//    HttpHeaders headers;
//
//    @BeforeEach
//    void setUp() {
//        // Set the base URL
//        baseUrl = "http://localhost:" + port;
//
//        // Set common headers for all tests
//        headers = new HttpHeaders();
//        headers.set("Accept", "application/json");
//    }
//
//    @Test
//    void registerSuccess_userNotExist() {
//        var path = getUrl("/api/register");
//        // Use common headers set in setUp() method
//        var request = new HttpEntity<>(prepareTestSignUpRequestDto("mobark@news360horizon.com"), headers);
//
//        var response = restTemplate.postForEntity(path, request, UserEntity.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//    }
//
//    @Test
//    void registerFailed_userExist() {
//        var path = getUrl("/api/register");
//        var request = new HttpEntity<>(prepareTestSignUpRequestDto("test@news360horizon.com"), headers);
//
//        var response = restTemplate.postForEntity(path, request, String.class);
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertNotNull(response.getBody());
//    }
//
//    @Test
//    void loginSuccess() {
//        var path = getUrl("/api/login");
//        var request = new HttpEntity<>(
//                new LoginRequestDto("test@news360horizon.com", "1234"),
//                headers
//        );
//
//        var response = restTemplate.postForEntity(path, request, JwtResponse.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals("Bearer", response.getBody().type());
//    }
//
//    @Test
//    void loginFailed_wrongCredential() {
//        var path = getUrl("/api/login");
//        var request = new HttpEntity<>(
//                new LoginRequestDto("test@news360horizon.com", "wrong@1234"),
//                headers
//        );
//
//        try {
//            restTemplate.postForEntity(path, request, JwtResponse.class);
//        } catch (ResourceAccessException e) {
//            assertEquals(HttpStatus.UNAUTHORIZED.value(), ((HttpRetryException) e.getCause()).responseCode());
//        }
//    }
//
//    String getUrl(String path) {
//        return baseUrl + path;
//    }
//
//    SignUpRequestDto prepareTestSignUpRequestDto(String email) {
//        return new SignUpRequestDto(
//                email,
//                "test@1234",
//                Set.of(1L),
//                "Baridhara, Dhaka",
//                "test",
//                "01700000000"
//        );
//    }
//}