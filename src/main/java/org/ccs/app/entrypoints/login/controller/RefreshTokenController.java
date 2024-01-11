package org.ccs.app.entrypoints.login.controller;

import org.ccs.app.core.security.JwtTokenProvider;
import org.ccs.app.core.user.infra.repository.UserRepository;
import org.ccs.app.entrypoints.login.model.RefreshTokenRequest;
import org.ccs.app.entrypoints.login.model.TokenResponse;
import org.ccs.app.entrypoints.login.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/auth")
public class RefreshTokenController {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        // 리프레시 토큰 유효성 검사 로직 구현
        String refreshToken = refreshTokenRequest.getRefreshToken();

        // 리프레시 토큰의 유효성을 검사합니다.
        if (!refreshTokenService.isRefreshTokenValid(refreshToken)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid refresh token");
        }

        // 유효한 리프레시 토큰인 경우, 새로운 액세스 토큰 및 리프레시 토큰을 발급합니다.
        String newAccessToken = generateAccessToken(refreshToken);
        String newRefreshToken = generateRefreshToken();
        return ResponseEntity.ok(new TokenResponse(newAccessToken, newRefreshToken));
    }


    private String generateAccessToken(String refreshToken) {
        // 리프레시 토큰을 사용하여 새로운 액세스 토큰을 생성하는 로직 구현
        // 생성된 액세스 토큰 반환
    }

    private String generateRefreshToken() {
        // 새로운 리프레시 토큰을 생성하는 로직 구현
        // 생성된 리프레시 토큰 반환
    }
}
