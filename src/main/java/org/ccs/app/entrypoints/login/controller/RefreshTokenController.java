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

        // 리프레시 토큰 유효성 검사
        if (!refreshTokenService.isRefreshTokenValid(refreshToken)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid refresh token");
        }

        // 새로운 액세스 토큰 생성
        String newAccessToken = tokenProvider.generateAccessTokenUsingRefreshToken(refreshToken);

        // 새로운 리프레시 토큰 생성
        String newRefreshToken = tokenProvider.generateNewRefreshToken(refreshToken);

        return ResponseEntity.ok(new TokenResponse(newAccessToken, newRefreshToken));
    }
}
