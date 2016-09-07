package ru.mipt.restaurant.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class SecureController {


    @RequestMapping("/all")
    public String getAllSecureInfo() {
        return "This is secured";
    }
}

