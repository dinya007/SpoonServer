package ru.mipt.restaurant.server.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import ru.mipt.restaurant.server.ElasticTest;
import ru.mipt.restaurant.server.domain.Owner;


public class AuthControllerTest extends ElasticTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void login() throws Exception {
        ResponseEntity<String> allPlaces = restTemplate.getForEntity("/places/all", String.class);
        System.out.println(allPlaces);

        ResponseEntity<String> registerResponse = restTemplate.postForEntity("/authentication/register", Owner.builder().login("newLogin").email("newEmail").name("Name").password("newPassword").phone("+71234567890").build(), String.class);
        System.out.println(registerResponse);

        ResponseEntity<String> optionsResponse = restTemplate.exchange("/authentication/login?username=newLogin&password=newPassword", HttpMethod.OPTIONS, null, String.class);
        System.out.println("Headers: " + optionsResponse.getHeaders());

        ResponseEntity<String> response = restTemplate.postForEntity("/authentication/login?username=newLogin&password=newPassword", "", String.class);
        System.out.println(response);

        HttpHeaders headers = new HttpHeaders();
        String J_SESSION_ID = response.getHeaders().get("Set-Cookie").get(0).split(";")[0];
        headers.add("Cookie", J_SESSION_ID);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> secureResponse = restTemplate.exchange("/secure/all", HttpMethod.GET, entity, String.class);
        System.out.println(secureResponse);

        ResponseEntity<String> logoutResponse = restTemplate.exchange("/authentication/logout", HttpMethod.POST, new HttpEntity<>(null, headers), String.class);
        System.out.println(logoutResponse);

        try {
            ResponseEntity<String> secureResponse2 = restTemplate.exchange("/secure/all", HttpMethod.GET, entity, String.class);
            System.out.println(secureResponse2);
        } catch (HttpClientErrorException exception) {
            Assert.assertEquals("401 Unauthorized", exception.getMessage());
        }

    }

}