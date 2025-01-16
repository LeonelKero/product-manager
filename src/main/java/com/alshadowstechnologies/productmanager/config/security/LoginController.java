package com.alshadowstechnologies.productmanager.config.security;

import com.alshadowstechnologies.productmanager.config.security.jwt.AccountCredentials;
import com.alshadowstechnologies.productmanager.config.security.jwt.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(path = {"/login"})
    public ResponseEntity<?> getLoginAndGetToken(final @RequestBody AccountCredentials credentials) {
        final var creds = new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());
        final var auth = this.authenticationManager.authenticate(creds);
        final var token = this.jwtService.generateToken(auth.getName());
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                .build();
    }
}
