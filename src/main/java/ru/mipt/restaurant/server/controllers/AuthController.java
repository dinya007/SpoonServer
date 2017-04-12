package ru.mipt.restaurant.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.mipt.restaurant.server.controllers.dto.RegisterDto;
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
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (!isValidParams(registerDto))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ownerService.register(OwnerHelper.toOwner(registerDto));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean isValidParams(RegisterDto registerDto) {
        return !(StringUtils.isEmpty(registerDto.getName())
                || StringUtils.isEmpty(registerDto.getEmail())
                || StringUtils.isEmpty(registerDto.getPassword()));
    }

}
