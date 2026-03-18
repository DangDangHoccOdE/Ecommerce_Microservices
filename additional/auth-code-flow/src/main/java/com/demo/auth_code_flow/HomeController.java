package com.demo.auth_code_flow;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home(OAuth2AuthenticationToken token) {
        String roles = token.getAuthorities().toString();
        return "Welcome to the Home Page! " + token.getName() + ", "
                + token.getPrincipal().getAttributes().get("email") + ", Roles: " + roles;
    }
}