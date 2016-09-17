package ru.mipt.restaurant.server.controllers;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
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

        HttpHeaders headers = new HttpHeaders();
        String J_SESSION_ID = response.getHeaders().get("Set-Cookie").get(0).split(";")[0];
        headers.add("Cookie", J_SESSION_ID);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> secureResponse = restTemplate.exchange("http://127.0.0.1:8080/secure/all", HttpMethod.GET, entity, String.class);
        System.out.println(secureResponse);

        ResponseEntity<String> logoutResponse = restTemplate.exchange("http://127.0.0.1:8080/authentication/logout", HttpMethod.POST, new HttpEntity<>(null, headers), String.class);
        System.out.println(logoutResponse);

//        ResponseEntity<String> secureResponse2 = restTemplate.exchange("http://127.0.0.1:8080/secure/all", HttpMethod.GET, entity, String.class);
//        System.out.println(secureResponse2);

    }

}