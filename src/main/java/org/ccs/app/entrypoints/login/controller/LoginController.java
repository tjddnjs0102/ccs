package org.ccs.app.entrypoints.login.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ccs.app.entrypoints.login.model.JwtAuthenticationResponse;
import org.ccs.app.entrypoints.login.model.LoginRequest;
import org.ccs.app.entrypoints.login.service.CookieService;
import org.ccs.app.entrypoints.login.service.LoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginService loginService;
    private final CookieService cookieService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
                                                                      HttpServletResponse response) {
        JwtAuthenticationResponse jwtResponse = loginService.authenticate(loginRequest);
        cookieService.addCookie(response, "accessToken", jwtResponse.getAccessToken(), true);
        cookieService.addCookie(response, "refreshToken", jwtResponse.getRefreshToken(), false);
        return ResponseEntity.ok(jwtResponse);
    }
}
