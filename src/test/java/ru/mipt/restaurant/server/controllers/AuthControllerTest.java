package ru.mipt.restaurant.server.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.mipt.restaurant.server.controllers.dto.RegisterDto;

public class AuthControllerTest {

    private final String appUrl = "http://127.0.0.1:8081";

    @Test
    public void login() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> allPlaces = restTemplate.getForEntity(appUrl + "/places/all", String.class);
        System.out.println(allPlaces);

        ResponseEntity<String> registerResponse = restTemplate.postForEntity(appUrl + "/authentication/register", new RegisterDto("newLogin", "newEmail", "newPassword"), String.class);
        System.out.println(registerResponse);

        ResponseEntity<String> response = restTemplate.postForEntity(appUrl + "/authentication/login?username=newEmail&password=newPassword", "", String.class);
        System.out.println(response);

        HttpHeaders headers = new HttpHeaders();
        String J_SESSION_ID = response.getHeaders().get("Set-Cookie").get(0).split(";")[0];
        headers.add("Cookie", J_SESSION_ID);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> secureResponse = restTemplate.exchange(appUrl + "/secure/all", HttpMethod.GET, entity, String.class);
        System.out.println(secureResponse);

        ResponseEntity<String> logoutResponse = restTemplate.exchange(appUrl + "/authentication/logout", HttpMethod.POST, new HttpEntity<>(null, headers), String.class);
        System.out.println(logoutResponse);

        try {
            ResponseEntity<String> secureResponse2 = restTemplate.exchange(appUrl + "secure/all", HttpMethod.GET, entity, String.class);
            System.out.println(secureResponse2);
        } catch (HttpClientErrorException exception) {
            Assert.assertEquals("401 Unauthorized", exception.getMessage());
        }

    }

}