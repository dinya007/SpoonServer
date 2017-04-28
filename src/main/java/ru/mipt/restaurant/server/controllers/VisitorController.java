package ru.mipt.restaurant.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
import ru.mipt.restaurant.server.domain.Visitor;
import ru.mipt.restaurant.server.service.VisitorService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/visitor")
public class VisitorController {

    private static final String USER_COOKIE_NAME = "restaurant_uid";

    private final VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Visitor save(@RequestBody Visitor visitor, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = WebUtils.getCookie(request, USER_COOKIE_NAME);
        if (cookie == null) {
            String uid = visitorService.generateUID();
            visitor.setUid(uid);
            cookie = new Cookie(USER_COOKIE_NAME, uid);
        }
        Visitor result = visitorService.save(visitor);
        cookie.setMaxAge(getCookieExpirationTime());
        response.addCookie(cookie);
        return result;
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public Visitor get(@PathVariable String uid) {
        return visitorService.get(uid);
    }

    private int getCookieExpirationTime() {
        return (int) (TimeUnit.DAYS.toSeconds(30L));
    }

}
