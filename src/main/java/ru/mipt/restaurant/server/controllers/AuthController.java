package ru.mipt.restaurant.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.mipt.restaurant.server.controllers.dto.LoginDto;
import ru.mipt.restaurant.server.controllers.dto.RegisterDto;
import ru.mipt.restaurant.server.domain.Owner;
import ru.mipt.restaurant.server.service.OwnerService;
import ru.mipt.restaurant.server.utils.OwnerHelper;

@RestController
@RequestMapping("/authentication")
public class AuthController {

    private final OwnerService ownerService;

    @Autowired
    public AuthController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(RegisterDto registerDto) {
        if (!isValidParams(registerDto))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ownerService.register(OwnerHelper.toOwner(registerDto));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Owner> login(LoginDto loginDto) {
        if (!isValidParams(loginDto))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Owner owner = ownerService.get(loginDto.getLogin());

        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    private boolean isValidParams(RegisterDto registerDto) {
        return !(StringUtils.isEmpty(registerDto.getLogin())
                || StringUtils.isEmpty(registerDto.getEmail())
                || StringUtils.isEmpty(registerDto.getPassword()));
    }

    private boolean isValidParams(LoginDto loginDto) {
        return !(StringUtils.isEmpty(loginDto.getLogin())
                || StringUtils.isEmpty(loginDto.getEmail()));
    }

}
