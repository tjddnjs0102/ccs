package org.ccs.app.entrypoints.login.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ccs.app.entrypoints.login.model.JwtAuthenticationResponse;
import org.ccs.app.entrypoints.login.model.LoginRequest;
import org.ccs.app.entrypoints.login.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtAuthenticationResponse response = loginService.authenticate(loginRequest);
        return ResponseEntity.ok(response);
    }
}
