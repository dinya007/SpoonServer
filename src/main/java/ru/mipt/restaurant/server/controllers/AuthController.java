package ru.mipt.restaurant.server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.mipt.restaurant.server.controllers.dto.RegisterDto;

@RestController
@RequestMapping("/authentication")
public class AuthController {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(RegisterDto registerDto) {
        if (StringUtils.isEmpty(registerDto.getLogin())
                || StringUtils.isEmpty(registerDto.getEmail())
                || StringUtils.isEmpty(registerDto.getPassword()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        //TODO add user

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
