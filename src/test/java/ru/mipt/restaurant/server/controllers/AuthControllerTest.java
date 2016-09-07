package ru.mipt.restaurant.server.controllers;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import ru.mipt.restaurant.server.InMemoryApplication;
import ru.mipt.restaurant.server.controllers.dto.RegisterDto;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = InMemoryApplication.class)
//@WebAppConfiguration
@Ignore
public class AuthControllerTest {

    @Test
    public void login() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> allPlaces = restTemplate.getForEntity("http://127.0.0.1:8080/places/all", String.class);
        System.out.println(allPlaces);
        ResponseEntity<String> registerResponse = restTemplate.postForEntity("http://127.0.0.1:8080/authentication/register", new RegisterDto("newLogin", "newEmail", "newPassword"), String.class);
        System.out.println(registerResponse);
        ResponseEntity<String> response = restTemplate.postForEntity("http://127.0.0.1:8080/authentication/login?username=newLogin&password=newPassword", "", String.class);
        System.out.println(response);
    }

}