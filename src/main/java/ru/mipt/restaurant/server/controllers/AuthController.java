package ru.mipt.restaurant.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.mipt.restaurant.server.domain.Owner;
import ru.mipt.restaurant.server.service.OwnerService;

@RestController
@RequestMapping("/authentication")
public class AuthController {

    private final OwnerService ownerService;

    @Autowired
    public AuthController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody Owner owner) {
        if (!isValidParams(owner))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ownerService.register(owner);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean isValidParams(Owner owner) {
        return !(StringUtils.isEmpty(owner.getName())
                || StringUtils.isEmpty(owner.getEmail())
                || StringUtils.isEmpty(owner.getLogin())
                || StringUtils.isEmpty(owner.getPhone())
                || StringUtils.isEmpty(owner.getPassword()));
    }

}
