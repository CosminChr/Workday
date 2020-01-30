package com.cosmin.licenta.workday.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class UserInfoController {

    @RequestMapping(method = RequestMethod.GET, value = "/users/info")
    public Map<String, Object> getExtraInfo(@AuthenticationPrincipal Jwt jwt) {
        return Collections.singletonMap("user_name", jwt.getClaimAsString("user_name"));
    }
}
