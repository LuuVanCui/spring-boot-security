package com.security.springsecutity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        return "login";
    }
}
