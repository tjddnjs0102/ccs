package org.ccs.app.entrypoints.login.controller;

import jakarta.validation.Valid;
import org.ccs.app.entrypoints.login.model.JwtAuthenticationResponse;
import org.ccs.app.entrypoints.login.model.LoginRequest;
import org.ccs.app.entrypoints.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            String jwt = loginService.authenticate(loginRequest);
            String refreshToken = loginService.createRefreshToken(loginRequest); // 리프레시 토큰 생성
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, refreshToken));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred");
        }
    }
}
